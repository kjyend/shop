package shopprj.shop.domain.dto;

import lombok.Builder;
import lombok.Getter;
import shopprj.shop.domain.entity.Cart;
import shopprj.shop.domain.entity.Item;

import java.util.List;

@Builder
@Getter
public class ItemDto {

    private String name;
    private Long price;
    private Long stockQuantity;
    private List<Cart> cart;

    public Item toItemEntity() {
        return Item.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .cart(cart).build();
    }
}
