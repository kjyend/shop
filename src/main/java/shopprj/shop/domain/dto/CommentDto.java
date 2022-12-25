package shopprj.shop.domain.dto;

import lombok.Builder;
import lombok.Getter;
import shopprj.shop.domain.entity.Comment;
import shopprj.shop.domain.entity.Member;

import javax.validation.constraints.NotBlank;


@Builder
@Getter
public class CommentDto {
    @NotBlank
    private Integer point;
    @NotBlank
    private String talk;

    private Member member;

    public Comment toCommentEntity(MemberDto memberDto){
        return Comment.builder()
                .talk(talk)
                .point(point)
                .member(member).build();
    }
}
