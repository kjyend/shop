package shopprj.shop.domain.dto;

import lombok.Builder;
import lombok.Getter;
import shopprj.shop.domain.entity.Comment;

@Builder
@Getter
public class CommentDto {

    private Long point;
    private String talk;

    public Comment toCommentEntity(){
        return Comment.builder()
                .talk(talk)
                .point(point).build();
    }
}
