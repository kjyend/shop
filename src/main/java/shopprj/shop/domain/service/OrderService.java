package shopprj.shop.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopprj.shop.domain.dto.ItemDto;
import shopprj.shop.domain.dto.OrderDto;
import shopprj.shop.domain.entity.Order;
import shopprj.shop.domain.repository.OrderRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    
    public void cartCheck(ItemDto itemDto){
        //원하는 물건 담기

    }

    public void OrderItem(OrderDto orderDto, ItemDto itemDto){
        //중간에 save하는 과정이 필요하다.
        Order order = orderDto.toOrderEntity();
        orderRepository.save(order);
    }


}
