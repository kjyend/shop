package shopprj.shop.domain.entity;

import javax.persistence.*;

@Entity
public class Cart {
    @Id @GeneratedValue
    @Column(name = "cart_id")
    private Long id;

    private Long cartCount; //상품을 원하는 갯수

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

}
