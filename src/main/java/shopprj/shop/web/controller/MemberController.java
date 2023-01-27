package shopprj.shop.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shopprj.shop.dto.DeliveryDto;
import shopprj.shop.dto.ItemDto;
import shopprj.shop.dto.MemberDto;
import shopprj.shop.dto.OrderItemDto;
import shopprj.shop.service.ItemService;
import shopprj.shop.service.MemberService;
import shopprj.shop.service.OrderService;
import shopprj.shop.web.argumentresolver.Login;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    private final ItemService itemService;

    private final OrderService orderService;

    @GetMapping("/members/{memberId}")//mypage/{memberId}
    public String MypageForm(@Login MemberDto memberDto, DeliveryDto deliveryDto, Model model){
        //여기서 에러가 나온다. 일단 대기를 해야할듯
        //findAll이 아니라 구매할때 넣는것을 넣어야할듯
        //member에서
        model.addAttribute("member", memberDto);
        model.addAttribute("delivery",deliveryDto);
        return "mypage/MyPage";
    }

    @PostMapping("/members/{memberId}")
    public ResponseEntity<Boolean> checkLoginIdDuplicate(@PathVariable("memberId") Long memberId){
        //다른 걸로 확인을 해야하나? 고민해야한다. ?? 중복 안됨; 뭐야;
        //할때 확인을 해야한다. 아 html에서 해야하는걸 안했다.
        return ResponseEntity.ok(memberService.checkLoginIdDuplicate(memberId));
    }

    @GetMapping("/members/{memberId}/edit")//edit/{memberId}
    public String EditForm(@PathVariable("memberId") Long memberId, @Login MemberDto memberDto, Model model){
        model.addAttribute("member",memberDto);
        return "mypage/Edit";
    }

    @PostMapping("/members/{memberId}/edit")
    public String Edit(@PathVariable("memberId") Long memberId, @Validated MemberDto memberDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "mypage/Edit";
        }

        boolean update = memberService.update(memberId, memberDto);

        if(update){
            return "redirect:/edit/{memberId}";
        }

        return "redirect:/";
    }

    @GetMapping("/members/{memberId}/orders")//list/{memberId}
    public String ListForm(@Login MemberDto memberDto,@PathVariable("memberId") Long memberId,Model model){
        // order에서 status에 order인경우+자신의 리스트만 list에서 나오게 해야한다.
        //query를 만들어서 쏴야할듯하다.

        //order에 member와 연동해서 찾아야한다.join에 하면서 해야할것 같다.
        List<ItemDto> all = itemService.findAll();

        List<OrderItemDto> orderItem = orderService.listOrder(memberId);

        model.addAttribute("member",memberDto);
        model.addAttribute("item", all);
        model.addAttribute("orderItem", orderItem);

        return "mypage/orderList";
    }

    @PostMapping("/members/{memberId}/orders/delete")
    public String Cancel(@RequestParam("orderId") Long orderId, @RequestParam("stock") Integer stock, @RequestParam("itemId") Long itemId){
        //내가 주문한 item을 취소한다.
        orderService.orderCancel(orderId,stock,itemId);
        return "redirect:/";
    }

}
