package shopprj.shop.domain.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import shopprj.shop.domain.dto.OrderDto;
import shopprj.shop.domain.dto.OrderItemDto;
import shopprj.shop.domain.entity.QItem;
import shopprj.shop.domain.entity.QOrder;

import java.util.List;

import static shopprj.shop.domain.entity.QItem.item;
import static shopprj.shop.domain.entity.QOrder.order;
import static shopprj.shop.domain.entity.QOrderItem.orderItem;


@RequiredArgsConstructor
public class OrderItemRepositoryImpl implements OrderItemRepositoryCustom{

    private final JPAQueryFactory jpaQueryFactory;

    //메서드 구현
    @Override
    public List<OrderItemDto> getOrderItemList(Long memberId) {

        return jpaQueryFactory.select(Projections.constructor(OrderItemDto.class,
                         orderItem.orderPrice
                        ,orderItem.count))
                .from(orderItem)
                .join(orderItem.order, order).on(order.member.id.eq(memberId))
                .fetch();//querydsl 살펴볼것

    }
}
