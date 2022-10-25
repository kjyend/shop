package shopprj.shop.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shopprj.shop.domain.dto.MemberDto;
import shopprj.shop.domain.service.MemberService;
import shopprj.shop.web.argumentresolver.Login;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/Mypage/{id}")
    public String MypageForm(@Login MemberDto loginMember, Model model){
        model.addAttribute("member", loginMember);
        return "mypage/MyPage";
    }

    @GetMapping("/Mypage-loginId/{loginId}")
    public ResponseEntity<Boolean> checkLoginIdDuplicate(@PathVariable("loginId") String loginId){
        //다른 걸로 확인을 해야하나? 고민해야한다. ?? 중복 안됨; 뭐야;
        return ResponseEntity.ok(memberService.checkLoginIdDuplicate(loginId));
    }

    @GetMapping("/Edit/{id}")
    public String EditForm(@PathVariable Long id, @Login MemberDto loginMember, Model model){
        model.addAttribute("member",loginMember);
        return "mypage/Edit";
    }

    @PostMapping("/Edit/{id}")
    public String Edit(@PathVariable String id, MemberDto loginMember){
        memberService.update(id,loginMember);
        return "redirect:/Mypage/"+id;
    }

}
