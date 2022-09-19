package shopprj.shop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {
    @PostMapping("/Comment")//따로 버튼을 만들고 post사용시 댓글을 달 수 있다.
    public String Comment(){
        return "redirect:/";
    }
}
