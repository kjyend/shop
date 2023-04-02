package shopprj.shop.repository.order;

import shopprj.shop.dto.OrderItemDto;

import java.util.List;

public interface OrderItemRepositoryCustom {
    //추가적으로 인터페이스의 값을 넣을것
    List<OrderItemDto> getOrderItemList(Long memberId);
}
