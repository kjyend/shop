package shopprj.shop.domain.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Comment {
    @Id @GeneratedValue
    private Long id;

    private Long point;
    private String talk;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;
}
