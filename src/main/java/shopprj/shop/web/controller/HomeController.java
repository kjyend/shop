package shopprj.shop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import shopprj.shop.domain.dto.MemberDto;
import shopprj.shop.web.argumentresolver.Login;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homeForm(@Login MemberDto loginMember, Model model) {
        if (loginMember == null) {
            return "Home";
        }
        model.addAttribute("member",loginMember);
        return "LoginHome";
    }
}
