package shopprj.shop.domain.dto;

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
    private Delivery delivery;


    public Order toOrderEntity(Member member,Delivery delivery){
        return Order.builder()
                .member(member)
                .delivery(delivery)
                .createdDate(LocalDateTime.now())
                .status(OrderStatus.ORDER).build();
    }

}
