package shopprj.shop.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shopprj.shop.domain.dto.CommentDto;
import shopprj.shop.domain.dto.DeliveryDto;
import shopprj.shop.domain.dto.ItemDto;
import shopprj.shop.domain.dto.MemberDto;
import shopprj.shop.domain.service.CommentService;
import shopprj.shop.domain.service.ItemService;
import shopprj.shop.domain.service.MemberService;
import shopprj.shop.web.argumentresolver.Login;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final ItemService itemService;

    private final MemberService memberService;

    private final CommentService commentService;

    @GetMapping("/Buy")
    public String BuyForm(@Login MemberDto loginMember,ItemDto itemDto, CommentDto commentDto, Model model){
        ItemDto buyItem = itemService.findBuyItem(itemDto);//dto로 바꾸어서 다시 나오게 해야한다. 그리고 출력해야한다. 그리고 model값에 넣는다.
//금요일에 stream으로 한번에해서 전부 열기
        List<CommentDto> talk = commentService.findTalk();
        //stream으로 해결해야한다.
        model.addAttribute("comments",talk);
        model.addAttribute("member", loginMember);
        model.addAttribute("comment",commentDto);
        model.addAttribute("item",buyItem);
        return "buy/Buy";
    }

    @PostMapping("/Buy")
    public String Buy(ItemDto itemDto){//총 가격, 주소 넣어서,
        //item에서 buy하면 item에서 표시할게 아니라 member에서 해야할듯
//        itemService.buyItem();
        return "redirect:/";
    }

    @GetMapping("/Cart")
    public String CartForm(@Login MemberDto loginMember, ItemDto itemDto, Model model){
        if(loginMember==null){
            return "login/Login";
        }
        List<ItemDto> all = itemService.findAll();//dto로 바꾸어서 다시 나오게 해야한다. 그리고 출력해야한다. 그리고 model값에 넣는다.
        //금요일에 stream으로 한번에해서 전부 열기
        //선호하는것만 뽑아야한다.
        model.addAttribute("member", loginMember);
        model.addAttribute("item",all);
        return "buy/Cart";
    }

    @PostMapping("/Cart")
    public String Cart(ItemDto itemDto){
        //cart로 자신의 id를 넣는다던가 아니면 다른 식으로 표현해야한다.
        itemService.cartItem();
        return "redirect:/";
    }


    @GetMapping("/Bill")
    public String InvoiceForm(@Login MemberDto loginMember, ItemDto itemDto, DeliveryDto deliveryDto, Model model){
        model.addAttribute("delivery",deliveryDto);
        model.addAttribute("member", loginMember);
        model.addAttribute("item",itemDto);
        return "bill/Bill";
    }

    @GetMapping("Success")
    public String SuccessForm(@Login MemberDto loginMember,ItemDto itemDto, DeliveryDto deliveryDto ,Model model){
        model.addAttribute("delivery",deliveryDto);
        model.addAttribute("item",itemDto);
        model.addAttribute("member", loginMember);
        return "bill/Success";
    }

    @GetMapping("/Fail")
    public String FailForm(@Login MemberDto loginMember, Model model){
        model.addAttribute("member", loginMember);
        return "bill/Fail";
    }
}
