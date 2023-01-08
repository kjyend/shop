package shopprj.shop.domain.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopprj.shop.domain.dto.CommentDto;
import shopprj.shop.domain.dto.ItemDto;
import shopprj.shop.domain.dto.MemberDto;
import shopprj.shop.domain.entity.Comment;
import shopprj.shop.domain.entity.Item;
import shopprj.shop.domain.entity.Member;
import shopprj.shop.domain.repository.CommentRepository;
import shopprj.shop.domain.repository.ItemRepository;
import shopprj.shop.domain.repository.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;

    private final MemberRepository memberRepository;

    private final ItemRepository itemRepository;

    public List<CommentDto> findTalk(){
        List<Comment> all = commentRepository.findAll();
        List<CommentDto> commentList = all.stream()
                .map(Comment::toCommentDto).collect(Collectors.toList());
        return commentList;
    }

    public void save(CommentDto commentDto, Long itemId, MemberDto memberDto) {
        log.info("2=2={}",memberDto.getId());
        Member member = memberRepository.findById(memberDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("회원이 없다."));

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException());
        Comment comment = Comment.builder()
                .talk(commentDto.getTalk())
                .point(commentDto.getPoint())
                .member(member)
                .item(item)
                .build();
        log.info("=={}",comment.getMember());
        commentRepository.save(comment);
    }
}
