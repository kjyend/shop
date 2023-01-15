package shopprj.shop.dto;

import lombok.Builder;
import lombok.Getter;
import shopprj.shop.domain.entity.Order;

@Builder
@Getter
public class OrderItemDto {

    private Long id;
    private Integer orderPrice;
    private Integer count;
    private Order order;

    public OrderItemDto(Long id, Integer orderPrice, Integer count, Order order) {
        this.id = id;
        this.orderPrice = orderPrice;
        this.count = count;
        this.order = order;
    }
}
