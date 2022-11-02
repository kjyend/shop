package shopprj.shop.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import shopprj.shop.domain.dto.CommentDto;
import shopprj.shop.domain.dto.ItemDto;
import shopprj.shop.domain.dto.MemberDto;

@Controller
public class CommentController {
    //댓글은 Buy부분에 리뷰를 만들어서 한다.
    @PostMapping("/Comment")//따로 버튼을 만들고 post사용시 댓글을 달 수 있다.
    public String Comment(MemberDto member, ItemDto itemDto, CommentDto comment){

        return "redirect:/";
    }
}
