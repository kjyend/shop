package shopprj.shop.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shopprj.shop.domain.dto.*;
import shopprj.shop.domain.service.CommentService;
import shopprj.shop.domain.service.ItemService;
import shopprj.shop.domain.service.OrderService;
import shopprj.shop.web.argumentresolver.Login;

import javax.validation.Valid;
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
    public String BuyForm(@Login MemberDto loginMember, ItemDto itemDto, CommentDto commentDto, Model model){
        if(loginMember==null){
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
        model.addAttribute("commentDto",commentDto);
        model.addAttribute("itemDto",itemDto);
        model.addAttribute("points",points);
        return "buy/Buy";
    }

    //생략해야할것 같다.
    @PostMapping("/Buy")
    public String Buy(@Valid ItemDto itemDto, BindingResult bindingResult, Model model){//총 가격, 주소 넣어서,
        return "redirect:/Bill";
    }

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
        log.info("12={}",itemDto.getItemName());
        log.info("11={}",itemDto.getStockQuantity());
        model.addAttribute("deliveryDto",deliveryDto);
        model.addAttribute("member", loginMember);
        model.addAttribute("itemDto",itemDto);
        return "bill/Bill";
    }

    //Buy Post부분을 여기에 넣어야할것 같다고 생각한다.
    @PostMapping("/Bill")
    public String Invoice(MemberDto loginMember, ItemDto itemDto, @Valid DeliveryDto deliveryDto,
                          BindingResult bindingResult, RedirectAttributes redirectAttributes){

        //null이므로 나중에 값을 정하고 푼다.
//        Boolean aBoolean = itemService.countSubtract(itemDto);
//        if(aBoolean.equals(false)){
//            return "redirect:/Fail";
//        }


        redirectAttributes.addFlashAttribute("deliveryDto",deliveryDto);
        redirectAttributes.addFlashAttribute("member", loginMember);
        redirectAttributes.addFlashAttribute("itemDto",itemDto);

        //먼저 delivery의 find를 하고 없으면 save해서 값을 낸다.
        return "redirect:/Success";
    }


    @GetMapping("Success")
    public String SuccessForm(@Login MemberDto loginMember,ItemDto itemDto, DeliveryDto deliveryDto ,Model model){
        log.info("111={}",deliveryDto.getStreet());
        model.addAttribute("deliveryDto",deliveryDto);
        model.addAttribute("itemDto",itemDto);
        model.addAttribute("member", loginMember);
        return "bill/Success";
    }

    @GetMapping("/Fail")
    public String FailForm(@Login MemberDto loginMember, Model model){
        model.addAttribute("member", loginMember);
        return "bill/Fail";
    }
}
