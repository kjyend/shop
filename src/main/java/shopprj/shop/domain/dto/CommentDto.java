package shopprj.shop.domain.dto;

import lombok.Builder;
import lombok.Getter;
import shopprj.shop.domain.entity.Comment;

import javax.validation.constraints.NotBlank;


@Builder
@Getter
public class CommentDto {
    @NotBlank
    private Integer point;
    @NotBlank
    private String talk;

    public Comment toCommentEntity(){
        return Comment.builder()
                .talk(talk)
                .point(point).build();
    }
}
