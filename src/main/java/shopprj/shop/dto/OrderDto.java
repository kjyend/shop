package shopprj.shop.dto;

import lombok.Builder;
import lombok.Getter;
import shopprj.shop.domain.entity.Delivery;
import shopprj.shop.domain.entity.Member;
import shopprj.shop.domain.entity.Order;
import shopprj.shop.domain.entity.status.OrderStatus;

import java.time.LocalDateTime;

@Builder
@Getter
public class OrderDto {

    private Long id;
    private OrderStatus status;
    private Member member;
    private LocalDateTime createDate;


    public Order toOrderEntity(Member member){
        return Order.builder()
                .member(member)
                .createdDate(LocalDateTime.now())
                .status(OrderStatus.ORDER).build();
    }

}
