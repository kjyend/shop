package shopprj.shop.domain.dto;

import lombok.Builder;
import lombok.Getter;
import shopprj.shop.domain.entity.Cart;
import shopprj.shop.domain.entity.Item;
import shopprj.shop.domain.entity.OrderItem;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class ItemDto {

    private String name;
    private Integer price;
    private Integer stockQuantity;
    private List<OrderItem> orderItems=new ArrayList<>();

    public Item toItemEntity() {
        return Item.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .orderItems(orderItems)
                .build();
    }
}
