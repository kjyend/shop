package shopprj.shop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shopprj.shop.domain.dto.MemberDto;
import shopprj.shop.web.argumentresolver.Login;

@Controller
public class MemberController {

    @GetMapping("/Mypage")
    public String MypageForm(@Login MemberDto loginMember, Model model){
        model.addAttribute("member", loginMember);
        return "mypage/MyPage";
    }

    @GetMapping("/Edit")
    public String EditForm(@Login MemberDto loginMember, Model model){
        model.addAttribute("member",loginMember);
        return "mypage/Edit";
    }
    @PostMapping
    public String Edit(@Login MemberDto loginMember){
        //repository를 통해서 수정하기 생각해야한다.
        return "redirect:/";
    }

}
