package shopprj.shop.domain.entity;

import javax.persistence.*;

@Entity
public class Cart {
    @Id @GeneratedValue
    private String id;

    private Long cartCount;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
