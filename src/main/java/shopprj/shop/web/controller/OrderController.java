package shopprj.shop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shopprj.shop.domain.dto.ItemDto;

@Controller
public class OrderController {

    @GetMapping("/Buy")
    public String BuyForm(ItemDto itemDto, Model model){

        return "buy/Buy";
    }
    @PostMapping("/Buy")
    public String Buy(){
        return "redirect:/";
    }

    @GetMapping("/Cart")
    public String CartForm(){
        return "buy/Cart";
    }

    @PostMapping("/Cart")
    public String Cart(){
        return "redirect:/";
    }


    @GetMapping("/Bill")
    public String InvoiceForm(ItemDto itemDto,Model model){
        model.addAttribute("item",itemDto);
        return "bill/Bill";
    }

    @GetMapping("Success")
    public String SuccessForm(){

        return "invoice/Success";
    }

    @GetMapping("/Fail")
    public String FailForm(){
        return "invoice/Fail";
    }
}
