package shopprj.shop.dto;

import lombok.Getter;
import shopprj.shop.domain.entity.Cart;
import shopprj.shop.domain.entity.Member;
import shopprj.shop.domain.entity.status.CartStatus;

@Getter
public class CartDto {

    private Long id;
    private CartStatus status;
    private Member member;

    public Cart toCart(){
        return Cart.builder()
                .id(id)
                .status(CartStatus.LIKE).build();
    }

    public CartDto(CartStatus status) {
        this.status = status;
    }
}
