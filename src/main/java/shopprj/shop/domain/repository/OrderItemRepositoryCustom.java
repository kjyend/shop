package shopprj.shop.domain.repository;

import shopprj.shop.domain.dto.OrderItemDto;
import shopprj.shop.domain.entity.OrderItem;

import java.util.List;

public interface OrderItemRepositoryCustom {
    //추가적으로 인터페이스의 값을 넣을것
    List<OrderItemDto> getOrderItemList(Long memberId);
}
