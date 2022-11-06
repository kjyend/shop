package shopprj.shop.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shopprj.shop.domain.dto.CommentDto;
import shopprj.shop.domain.dto.MemberDto;
import shopprj.shop.domain.entity.Comment;
import shopprj.shop.domain.repository.CommentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public void pointCheck(){

    }
    public void talkCheck(){

    }
    public List<CommentDto> findTalk(){
        List<Comment> all = commentRepository.findAll();
        //stream 으로 전부 찾아내기
        return null;
    }

    public void save(CommentDto commentDto, MemberDto memberDto) {
        Comment comment = commentDto.toCommentEntity();
        commentRepository.save(comment);
    }
}
