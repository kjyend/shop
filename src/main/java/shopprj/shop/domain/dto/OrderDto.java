package shopprj.shop.domain.dto;

import lombok.Builder;
import lombok.Getter;
import shopprj.shop.domain.entity.Member;
import shopprj.shop.domain.entity.Order;
import shopprj.shop.domain.entity.status.OrderStatus;

import java.time.LocalDateTime;

@Builder
@Getter
public class OrderDto {

    private Long id;
    private LocalDateTime createdDate;
    private Member member;
    private OrderStatus status;

    public Order toOrderEntity(){
        return Order.builder()
                .id(id)
                .createdDate(createdDate)
                .member(member)
                .status(status).build();
    }

}
