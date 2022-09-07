package shopprj.shop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping("/Mypage")
    public String MypageForm(){
        return "mypage/MyPage";
    }
}
