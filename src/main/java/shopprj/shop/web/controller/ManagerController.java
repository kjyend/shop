package shopprj.shop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManagerController {

    @GetMapping("/shopManage")
    public String shopManageForm(){
        return "manager/ShopManager";
    }

    @GetMapping("/MemberManage")
    public String MemberMangeForm(){
        return "manager/MemberManager";
    }
}
