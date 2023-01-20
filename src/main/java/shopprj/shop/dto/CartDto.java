package shopprj.shop.dto;

import lombok.Getter;
import shopprj.shop.domain.entity.Item;
import shopprj.shop.domain.entity.Member;
import shopprj.shop.domain.entity.status.CartStatus;

@Getter
public class CartDto {

    private Long id;
    private CartStatus status;
    private Item item;
    private Member member;

    public CartDto(Long id,CartStatus status,Item item,Member member) {
        this.id=id;
        this.status = status;
        this.item=item;
        this.member=member;
    }
}
