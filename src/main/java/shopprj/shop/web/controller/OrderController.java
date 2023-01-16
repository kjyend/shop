package shopprj.shop.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shopprj.shop.dto.*;
import shopprj.shop.service.*;
import shopprj.shop.web.argumentresolver.Login;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {

    private final ItemService itemService;

    private final OrderService orderService;

    private final CommentService commentService;

    private final DeliveryService deliveryService;

    private final MemberService memberService;

    @GetMapping("/buy")
    public String BuyForm(@Login MemberDto loginMember, ItemDto itemDto, CommentDto commentDto, Model model){
        if(loginMember==null){
            return "redirect:/login";
        }
        //금요일에 stream으로 한번에해서 전부 열기
        List<CommentDto> talk = commentService.findTalk();
        //stream으로 해결해야한다.

        model.addAttribute("comments",talk);
        model.addAttribute("member", loginMember);
        model.addAttribute("commentDto",commentDto);
        model.addAttribute("itemDto",itemDto);
        return "buy/Buy";
    }

    @PostMapping("/buy")
    public String Buy(@Valid ItemDto itemDto, BindingResult bindingResult, RedirectAttributes redirectAttributes){//총 가격, 주소 넣어서,
        redirectAttributes.addFlashAttribute("itemDto",itemDto);
        redirectAttributes.addFlashAttribute("fullPrice",itemDto.getPrice()*itemDto.getStockQuantity());
        return "redirect:/bill";
    }

    @GetMapping("/cart/{memberId}")
    public String CartForm(@Login MemberDto loginMember,@PathVariable("memberId") Long memberId , Model model){
        if(loginMember==null){
            return "redirect:/login";
        }

        List<CartDto> cartDto = memberService.cartList(memberId);
        //dto로 바꾸어서 다시 나오게 해야한다. 그리고 출력해야한다. 그리고 model값에 넣는다.
        //금요일에 stream으로 한번에해서 전부 열기
        //선호하는것만 뽑아야한다.
        model.addAttribute("member", loginMember);
        model.addAttribute("cart",cartDto);

        return "buy/Cart";
    }

    @PostMapping("/cart")
    public String Cart(MemberDto memberDto, @PathParam("itemId") Long itemId, CartDto cartDto){
        log.info("member={}",memberDto.getId());
        memberService.cartSave(memberDto,itemId);
        return "redirect:/";
    }


    @GetMapping("/bill")
    public String InvoiceForm(@Login MemberDto loginMember, ItemDto itemDto, DeliveryDto deliveryDto, Model model){
        log.info("12={}",itemDto.getId());

        log.info("11={}",itemDto.getStockQuantity());
        log.info("11={}",itemDto.getItemName());
        model.addAttribute("deliveryDto",deliveryDto);
        model.addAttribute("member", loginMember);
        model.addAttribute("itemDto",itemDto);
        return "bill/Bill";
    }

    //Buy Post부분을 여기에 넣어야할것 같다고 생각한다.
    @PostMapping("/bill")
    public String Invoice(MemberDto loginMember, ItemDto itemDto, @PathParam("itemId") Long itemId,
                          @PathParam("fullPrice") Integer fullPrice, OrderDto orderDto, @Valid DeliveryDto deliveryDto,
                          BindingResult bindingResult, RedirectAttributes redirectAttributes){

        log.info("==={}",itemDto.getId());
        boolean checkSuccess = itemService.countSubtract(itemId,itemDto);

        log.info("333={}",deliveryDto.getStreet());
        Long deliveryId = deliveryService.saveDelivery(loginMember, deliveryDto);

        redirectAttributes.addFlashAttribute("deliveryDto",deliveryDto);
        redirectAttributes.addFlashAttribute("member", loginMember);
        redirectAttributes.addFlashAttribute("itemDto",itemDto);

        //먼저 delivery의 find를 하고 없으면 save해서 값을 낸다.
        if(checkSuccess) {
            //orderitem을 해야할지 order을 해야할지 고민된다.
            Long orderId = orderService.orderSave(orderDto,loginMember,deliveryId);
            orderService.orderItemSave(orderId,itemId,itemDto.getStockQuantity(),fullPrice);
            return "redirect:/success";
        }else {
            return "redirect:/fail";
        }
    }


    @GetMapping("success")
    public String SuccessForm(@Login MemberDto loginMember,ItemDto itemDto, DeliveryDto deliveryDto ,Model model){
        log.info("111={}",deliveryDto.getStreet());
        model.addAttribute("deliveryDto",deliveryDto);
        model.addAttribute("itemDto",itemDto);
        model.addAttribute("member", loginMember);
        model.addAttribute("fullPrice",itemDto.getPrice()*itemDto.getStockQuantity());
        return "bill/Success";
    }

    @GetMapping("/fail")
    public String FailForm(@Login MemberDto loginMember, Model model){
        model.addAttribute("member", loginMember);
        return "bill/Fail";
    }

    @PostMapping("/delete/item")
    public String delete(ItemDto itemDto){
        itemService.delete(itemDto);
        return "redirect:/";
    }


    @PostMapping("/cart/delete")
    public String cartDelete(@PathParam("cartId")Long cartId){
        log.info("cart=={}",cartId);
        memberService.cartCancel(cartId);
        return "redirect:/";
    }
}
