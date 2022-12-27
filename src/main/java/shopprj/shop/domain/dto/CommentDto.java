package shopprj.shop.domain.dto;

import lombok.Builder;
import lombok.Getter;
import shopprj.shop.domain.entity.Comment;
import shopprj.shop.domain.entity.Member;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Builder
@Getter
public class CommentDto {
    @NotNull(message = "별점을 주세요")
    private Integer point;
    @NotBlank(message = "댓글을 달아주세요")
    private String talk;

    private Member member;

    public Comment toCommentEntity(MemberDto memberDto){
        return Comment.builder()
                .talk(talk)
                .point(point)
                .member(member).build();
    }
}
