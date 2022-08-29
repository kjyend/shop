package shopprj.shop.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shopprj.shop.domain.dto.MemberDto;
import shopprj.shop.domain.service.LoginService;
import shopprj.shop.domain.service.MemberService;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final MemberService memberService;
    private final LoginService loginService;

    @GetMapping("/signup")
    public String signupForm(MemberDto memberDto, Model model) {
        model.addAttribute("member",memberDto);
        return "Login/signup";
    }
    @PostMapping("/signup")
    public String signup(@Validated MemberDto memberDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "Login/signup";
        }
        memberService.save(memberDto);
        return "redirect";
    }

}
