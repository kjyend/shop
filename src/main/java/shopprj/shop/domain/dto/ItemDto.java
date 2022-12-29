package shopprj.shop.domain.dto;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;
import shopprj.shop.domain.entity.Cart;
import shopprj.shop.domain.entity.Item;
import shopprj.shop.domain.entity.OrderItem;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class ItemDto {

    @NotBlank
    private String itemName;
    @NotNull
    @Range(min=1000,max=10000000)
    private Integer price;
    @NotNull(message = "갯수를 넣어주세요")
    @Max(value = 9999)
    private Integer stockQuantity;

    public Item toItemEntity() {
        return Item.builder()
                .itemName(itemName)
                .price(price)
                .stockQuantity(stockQuantity)
                .build();
    }
}
