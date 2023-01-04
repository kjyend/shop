package shopprj.shop.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shopprj.shop.domain.dto.DeliveryDto;
import shopprj.shop.domain.dto.ItemDto;
import shopprj.shop.domain.dto.MemberDto;
import shopprj.shop.domain.dto.OrderDto;
import shopprj.shop.domain.service.ItemService;
import shopprj.shop.domain.service.MemberService;
import shopprj.shop.domain.service.OrderService;
import shopprj.shop.web.argumentresolver.Login;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    private final ItemService itemService;

    private final OrderService orderService;

    @GetMapping("/Mypage/{id}")
    public String MypageForm(@Login MemberDto loginMember,DeliveryDto deliveryDto, Model model){
        //여기서 에러가 나온다. 일단 대기를 해야할듯
        //findAll이 아니라 구매할때 넣는것을 넣어야할듯
        //member에서
        List<ItemDto> all = itemService.findAll();
        model.addAttribute("member", loginMember);
        model.addAttribute("item",all);
        model.addAttribute("delivery",deliveryDto);
        return "mypage/MyPage";
    }

    @GetMapping("/Mypage-loginId/{loginId}")
    public ResponseEntity<Boolean> checkLoginIdDuplicate(@PathVariable("loginId") String loginId){
        //다른 걸로 확인을 해야하나? 고민해야한다. ?? 중복 안됨; 뭐야;
        //할때 확인을 해야한다. 아 html에서 해야하는걸 안했다.
        return ResponseEntity.ok(memberService.checkLoginIdDuplicate(loginId));
    }

    @GetMapping("/Edit/{id}")
    public String EditForm(@PathVariable Long id, @Login MemberDto loginMember, Model model){
        model.addAttribute("member",loginMember);
        return "mypage/Edit";
    }

    @PostMapping("/Edit/{id}")
    public String Edit(@PathVariable String id, MemberDto loginMember, RedirectAttributes redirectAttributes){
        memberService.update(id,loginMember);
        redirectAttributes.addAttribute("member",loginMember);
        return "redirect:/Mypage/"+id;
    }

    @GetMapping("/OrderList")
    public String ListForm(@Login MemberDto loginMember,Model model){
        // order에서 status에 order인경우+자신의 리스트만 list에서 나오게 해야한다.
        //query를 만들어서 쏴야할듯하다.
        List<ItemDto> all = itemService.findAll();
        model.addAttribute("member",loginMember);
        model.addAttribute("item", all);
        return "mypage/orderList";
    }

}
