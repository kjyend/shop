package shopprj.shop.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shopprj.shop.domain.dto.*;
import shopprj.shop.domain.service.CommentService;
import shopprj.shop.domain.service.ItemService;
import shopprj.shop.domain.service.OrderService;
import shopprj.shop.web.argumentresolver.Login;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {

    private final ItemService itemService;

    private final OrderService orderService;

    private final CommentService commentService;

    @GetMapping("/Buy")
    public String BuyForm(@Login MemberDto loginMember,ItemDto itemDto, CommentDto commentDto, Model model){
        if(loginMember==null){
            //새로운 itemform을 만들어서 그쪽ㅇ으로 보낸다. redirection으로 보내야한다.
            return "redirect:/login";
        }
        //금요일에 stream으로 한번에해서 전부 열기
        List<CommentDto> talk = commentService.findTalk();
        //stream으로 해결해야한다.

        List<Integer> points = new ArrayList<>();
        points.add(1);
        points.add(2);
        points.add(3);
        points.add(4);
        points.add(5);

        model.addAttribute("comments",talk);
        model.addAttribute("member", loginMember);
        model.addAttribute("comment",commentDto);
        model.addAttribute("item",itemDto);
        model.addAttribute("points",points);
        return "buy/Buy";
    }

    //생략해야할것 같다.
//    @PostMapping("/Buy")
//    public String Buy(OrderDto orderDto, ItemDto itemDto){//총 가격, 주소 넣어서,
//        log.info("111={}",itemDto.getStockQuantity());
//        log.info("112={}",itemDto.getName());
//        //item에서 buy하면 item에서 표시할게 아니라 member에서 해야할듯
//        //올때 orderDto로 받아야 한다.
//        //이것을 그냥 bill로 보넨다.
//        orderService.OrderItem(orderDto,itemDto);
//        return "redirect:/Bill";
//    }

    @GetMapping("/Cart")
    public String CartForm(@Login MemberDto loginMember, ItemDto itemDto, Model model){
        if(loginMember==null){
            return "redirect:/login";
        }
        List<ItemDto> all = itemService.findAll();
        //dto로 바꾸어서 다시 나오게 해야한다. 그리고 출력해야한다. 그리고 model값에 넣는다.
        //금요일에 stream으로 한번에해서 전부 열기
        //선호하는것만 뽑아야한다.
        model.addAttribute("member", loginMember);
        model.addAttribute("item",all);
        return "buy/Cart";
    }

    @PostMapping("/Cart")
    public String Cart(ItemDto itemDto,CartDto cartDto){
        //cart로 자신의 id를 넣는다던가 아니면 다른 식으로 표현해야한다.
        //cart를 저장하는데 member,item,cart가 연결되어야한다.
        orderService.cartSave(itemDto,cartDto);
        return "redirect:/";
    }


    @GetMapping("/Bill")
    public String InvoiceForm(@Login MemberDto loginMember, ItemDto itemDto, DeliveryDto deliveryDto, Model model){
        log.info("12={}",itemDto.getName());
        log.info("11={}",itemDto.getStockQuantity());
        model.addAttribute("delivery",deliveryDto);
        model.addAttribute("member", loginMember);
        model.addAttribute("item",itemDto);
        return "bill/Bill";
    }

    //Buy Post부분을 여기에 넣어야할것 같다고 생각한다.
    @PostMapping("/Bill")
    public String Invoice(ItemDto itemDto, DeliveryDto deliveryDto){

        Boolean aBoolean = itemService.countSubtract(itemDto);
        if(aBoolean.equals(false)){
            return "redirect:/Fail";
        }
        //일단 수량을 체크해서 수량이 없으면 fail을 내보낸다.
        //수량이 있으면 수량을 줄이고 success를 보낸다.
        //그리고 배달로 보내야한다.

        return "redirect:/Success";
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
