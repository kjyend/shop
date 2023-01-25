package shopprj.shop.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopprj.shop.domain.entity.Comment;
import shopprj.shop.domain.entity.Item;
import shopprj.shop.domain.entity.Member;
import shopprj.shop.dto.CommentDto;
import shopprj.shop.dto.MemberDto;
import shopprj.shop.repository.CommentRepository;
import shopprj.shop.repository.ItemRepository;
import shopprj.shop.repository.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;

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
                .map(comment -> new CommentDto(comment.getPoint(),comment.getTalk(),comment.getMember())).collect(Collectors.toList());

        return commentList;
    }

    public void save(CommentDto commentDto, Long itemId, MemberDto memberDto) {
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
        commentRepository.save(comment);
    }
}
