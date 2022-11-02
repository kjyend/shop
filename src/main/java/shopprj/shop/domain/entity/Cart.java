package shopprj.shop.domain.entity;

import javax.persistence.*;

@Entity
public class Cart {
    @Id @GeneratedValue
    private Long id;

    private Long cartCount; //상품을 원하는 갯수

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne
    private Item item;
}
