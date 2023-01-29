package shopprj.shop.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import shopprj.shop.dto.*;
import shopprj.shop.service.*;
import shopprj.shop.web.argumentresolver.Login;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final ItemService itemService;

    private final OrderService orderService;

    private final CommentService commentService;

    private final DeliveryService deliveryService;

    private final CartService cartService;

    @GetMapping("/items")//buy
    public String BuyForm(@Login MemberDto memberDto, ItemDto itemDto, CommentDto commentDto, Model model){
        if(memberDto==null){
            return "redirect:/login";
        }
        //금요일에 stream으로 한번에해서 전부 열기
        List<CommentDto> talk = commentService.findTalk();
        //stream으로 해결해야한다.

        model.addAttribute("comments",talk);
        model.addAttribute("member", memberDto);
        model.addAttribute("commentDto",commentDto);
        model.addAttribute("itemDto",itemDto);
        return "buy/Buy";
    }

    @PostMapping("/items")
    public String Buy(@Validated ItemDto itemDto, BindingResult bindingResult, RedirectAttributes redirectAttributes){//총 가격, 주소 넣어서,
        redirectAttributes.addFlashAttribute("itemDto",itemDto);
        redirectAttributes.addFlashAttribute("fullPrice",itemDto.getPrice()*itemDto.getStockQuantity());
        return "redirect:/items/bill";
    }

    @GetMapping("/cart/{memberId}")
    public String CartForm(@Login MemberDto memberDto,@PathVariable("memberId") Long memberId , Model model){
        if(memberDto==null){
            return "redirect:/login";
        }

        List<CartDto> cartDto = cartService.cartList(memberId);
        //dto로 바꾸어서 다시 나오게 해야한다. 그리고 출력해야한다. 그리고 model값에 넣는다.
        //금요일에 stream으로 한번에해서 전부 열기
        //선호하는것만 뽑아야한다.
        model.addAttribute("member", memberDto);
        model.addAttribute("cart",cartDto);

        return "buy/Cart";
    }

    @PostMapping("/cart/{memberId}")
    public String Cart(MemberDto memberDto, @RequestParam("itemId") Long itemId, CartDto cartDto){
        boolean cartCheck = cartService.cartCheck(memberDto.getId(), itemId);
        if(!cartCheck) {
            cartService.cartSave(memberDto, itemId);
        }
        return "redirect:/";
    }


    @GetMapping("/items/bill")//bill
    public String InvoiceForm(@Login MemberDto memberDto, ItemDto itemDto, DeliveryDto deliveryDto, Model model){

        model.addAttribute("deliveryDto",deliveryDto);
        model.addAttribute("member", memberDto);
        model.addAttribute("itemDto",itemDto);
        return "bill/Bill";
    }

    //Buy Post부분을 여기에 넣어야할것 같다고 생각한다.
    @PostMapping("/items/bill")
    public String Invoice(MemberDto memberDto, ItemDto itemDto, @RequestParam("itemId") Long itemId,
                          @RequestParam("fullPrice") Integer fullPrice, OrderDto orderDto, @Validated DeliveryDto deliveryDto,
                          BindingResult bindingResult, RedirectAttributes redirectAttributes){

        boolean checkSuccess = itemService.countSubtract(itemId,itemDto);

        deliveryService.saveDelivery(memberDto, deliveryDto);

        redirectAttributes.addFlashAttribute("deliveryDto",deliveryDto);
        redirectAttributes.addFlashAttribute("member", memberDto);
        redirectAttributes.addFlashAttribute("itemDto",itemDto);

        //먼저 delivery의 find를 하고 없으면 save해서 값을 낸다.
        if(checkSuccess) {
            //orderitem을 해야할지 order을 해야할지 고민된다.
            Long orderId = orderService.orderSave(orderDto,memberDto);
            orderService.orderItemSave(orderId,itemId,itemDto.getStockQuantity(),fullPrice);
            return "redirect:/items/success";
        }else {
            return "redirect:/items/fail";
        }
    }


    @GetMapping("/items/success")//success
    public String SuccessForm(@Login MemberDto loginMember,ItemDto itemDto, DeliveryDto deliveryDto ,Model model){

        model.addAttribute("deliveryDto",deliveryDto);
        model.addAttribute("itemDto",itemDto);
        model.addAttribute("member", loginMember);
        model.addAttribute("fullPrice",itemDto.getPrice()*itemDto.getStockQuantity());
        return "bill/Success";
    }

    @GetMapping("/items/fail")//fail
    public String FailForm(@Login MemberDto loginMember, Model model){
        model.addAttribute("member", loginMember);
        return "bill/Fail";
    }

    @PostMapping("/management/items/delete")//management-> item/delete
    public String itemDelete(ItemDto itemDto){
        itemService.delete(itemDto);
        return "redirect:/";
    }


    @PostMapping("/cart/{cartId}/delete")//cart/delete
    public String cartDelete(@RequestParam("cartId") Long cartId){
        cartService.cartCancel(cartId);
        return "redirect:/";
    }
}
