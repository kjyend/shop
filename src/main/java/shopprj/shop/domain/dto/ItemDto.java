package shopprj.shop.domain.dto;

import lombok.Builder;
import lombok.Getter;
import shopprj.shop.domain.entity.Item;

@Builder
@Getter
public class ItemDto {

    private String name;
    private int price;
    private int stockQuantity;

    public Item toItemEntity(ItemDto itemDto) {
        return Item.builder()
                .name(itemDto.getName())
                .price(itemDto.getPrice())
                .stockQuantity(itemDto.getStockQuantity()).build();
    }
}
