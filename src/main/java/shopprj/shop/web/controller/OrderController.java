package shopprj.shop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import shopprj.shop.domain.dto.ItemDto;

@Controller
public class OrderController {

    @GetMapping("/Buy")
    public String BuyForm(ItemDto itemDto, Model model){

        return "buy/Buy";
    }

    @GetMapping("/Cart")
    public String CartForm(){
        return "buy/Cart";
    }

    @GetMapping("/Invoice")
    public String InvoiceForm(){
        return "invoice/Invoice";
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
