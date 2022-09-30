package shopprj.shop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shopprj.shop.domain.dto.ItemDto;
import shopprj.shop.domain.dto.MemberDto;
import shopprj.shop.web.argumentresolver.Login;

@Controller
public class OrderController {

    @GetMapping("/Buy")
    public String BuyForm(@Login MemberDto loginMember, ItemDto itemDto, Model model){
        model.addAttribute("member", loginMember);
        model.addAttribute("item",itemDto);
        return "buy/Buy";
    }
    @PostMapping("/Buy")
    public String Buy(){//총 가격, 주소 넣어서,
        return "redirect:/";
    }

    @GetMapping("/Cart")
    public String CartForm(@Login MemberDto loginMember, ItemDto itemDto, Model model){
        model.addAttribute("member", loginMember);
        model.addAttribute("item",itemDto);
        return "buy/Cart";
    }

    @PostMapping("/Cart")
    public String Cart(){
        return "redirect:/";
    }


    @GetMapping("/Bill")
    public String InvoiceForm(@Login MemberDto loginMember, ItemDto itemDto, Model model){
        model.addAttribute("member", loginMember);
        model.addAttribute("item",itemDto);
        return "bill/Bill";
    }

    @GetMapping("Success")
    public String SuccessForm(@Login MemberDto loginMember, Model model){
        model.addAttribute("member", loginMember);
        return "bill/Success";
    }

    @GetMapping("/Fail")
    public String FailForm(@Login MemberDto loginMember, Model model){
        model.addAttribute("member", loginMember);
        return "bill/Fail";
    }
}
