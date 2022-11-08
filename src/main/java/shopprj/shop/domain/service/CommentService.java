package shopprj.shop.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shopprj.shop.domain.dto.CommentDto;
import shopprj.shop.domain.dto.MemberDto;
import shopprj.shop.domain.entity.Comment;
import shopprj.shop.domain.repository.CommentRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public List<CommentDto> findTalk(){
        List<Comment> all = commentRepository.findAll();
        List<CommentDto> commentList = all.stream()
                .map(Comment::toCommentDto).collect(Collectors.toList());
        return commentList;
    }

    public void save(CommentDto commentDto, MemberDto memberDto) {
        Comment comment = commentDto.toCommentEntity();
        commentRepository.save(comment);
    }
}
