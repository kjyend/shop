package shopprj.shop.domain.entity;

import lombok.*;
import shopprj.shop.domain.dto.CommentDto;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {
    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private Integer point;

    private String talk;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @Builder
    public Comment(Integer point, String talk, Item item, Member member) {
        this.point = point;
        this.talk = talk;
        this.item = item;
        this.member = member;
    }

    public CommentDto toCommentDto(){
        return CommentDto.builder()
                .talk(talk)
                .point(point)
                .build();
    }
}
