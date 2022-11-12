package shopprj.shop.domain.dto;

import lombok.Builder;
import lombok.Getter;
import shopprj.shop.domain.entity.Cart;

@Builder
@Getter
public class CartDto {

    private Long id;
    private Long cartCount;

    public Cart toCart(){
        return Cart.builder()
                .id(id)
                .cartCount(cartCount).build();
    }
}
