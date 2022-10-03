package shopprj.shop.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shopprj.shop.domain.dto.MemberDto;
import shopprj.shop.domain.service.MemberService;
import shopprj.shop.web.argumentresolver.Login;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

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
    @PostMapping("/Edit")
    public String Edit(MemberDto loginMember){
        //repository를 통해서 수정하기 생각해야한다.
        memberService.update(loginMember);
        return "redirect:/";
    }

}
