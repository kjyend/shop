package shopprj.shop.domain.entity;

import lombok.*;
import shopprj.shop.domain.dto.CartDto;
import shopprj.shop.domain.entity.status.CartStatus;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart {
    @Id @GeneratedValue
    @Column(name = "cart_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private CartStatus status;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Builder
    public Cart(Long id, CartStatus status, Member member, Item item) {
        this.id = id;
        this.status = status;
        this.member = member;
        this.item = item;
    }

}
