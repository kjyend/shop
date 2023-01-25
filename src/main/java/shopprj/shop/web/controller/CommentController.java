package shopprj.shop.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import shopprj.shop.dto.CommentDto;
import shopprj.shop.dto.ItemDto;
import shopprj.shop.dto.MemberDto;
import shopprj.shop.service.CommentService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    //댓글은 Buy부분에 리뷰를 만들어서 한다.
    @PostMapping("/comment")//따로 버튼을 만들고 post사용시 댓글을 달 수 있다.
    public String Comment(MemberDto memberDto, ItemDto itemDto,
                          @RequestParam("itemId") Long itemId, @Validated CommentDto commentDto,
                          BindingResult bindingResult, Model model){
        List<CommentDto> talk = commentService.findTalk();


        if(bindingResult.hasErrors()){
            model.addAttribute("comments",talk);
            model.addAttribute("member", memberDto);
            model.addAttribute("commentDto",commentDto);
            model.addAttribute("itemDto",itemDto);
            return "buy/Buy";
        }
        //Member를 저장해야한다. 객체로 변환해서 저장해야 할듯
        commentService.save(commentDto,itemId,memberDto);

        return "redirect:/";
    }
}
