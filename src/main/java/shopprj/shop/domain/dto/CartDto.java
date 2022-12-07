package shopprj.shop.domain.dto;

import lombok.Builder;
import lombok.Getter;
import shopprj.shop.domain.entity.Cart;
import shopprj.shop.domain.entity.status.CartStatus;

@Builder
@Getter
public class CartDto {

    private Long id;
    private Long cartCount;
    private CartStatus status;

    public Cart toCart(){
        return Cart.builder()
                .id(id)
                .cartCount(cartCount)
                .status(status).build();
    }
}
