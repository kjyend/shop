package shopprj.shop.dto;

import lombok.Builder;
import lombok.Getter;
import shopprj.shop.domain.entity.Item;
import shopprj.shop.domain.entity.Order;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Builder
@Getter
public class OrderItemDto {

    private Long id;
    @Min(1000)
    private Integer orderPrice;
    @Size(min = 1,max = 100)
    private Integer count;
    private Order order;
    private Item item;

    public OrderItemDto(Long id, Integer orderPrice, Integer count, Order order,Item item) {
        this.id = id;
        this.orderPrice = orderPrice;
        this.count = count;
        this.order = order;
        this.item=item;
    }
}
