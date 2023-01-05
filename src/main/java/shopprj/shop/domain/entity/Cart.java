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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @Builder
    public Cart(Long id, CartStatus status, Member member, Item item) {
        this.id = id;
        this.status = status;
        this.member = member;
        this.item = item;
    }

    public CartDto toCartDto(){
        return CartDto.builder()
                .id(id)
                .status(CartStatus.LIKE).build();
    }
}
