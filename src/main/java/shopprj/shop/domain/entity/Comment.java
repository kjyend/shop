package shopprj.shop.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shopprj.shop.domain.dto.CommentDto;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private Long point;
    private String talk;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    public CommentDto toCommentDto(){
        return CommentDto.builder()
                .talk(talk)
                .point(point)
                .build();
    }
}
