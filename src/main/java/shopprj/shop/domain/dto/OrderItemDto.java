package shopprj.shop.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderItemDto {

    private Integer orderPrice;
    private Integer count;

    public OrderItemDto(Integer orderPrice, Integer count) {
        this.orderPrice = orderPrice;
        this.count = count;
    }
}
