package shopprj.shop.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopprj.shop.domain.dto.CartDto;
import shopprj.shop.domain.dto.ItemDto;
import shopprj.shop.domain.dto.OrderDto;
import shopprj.shop.domain.entity.Cart;
import shopprj.shop.domain.entity.Order;
import shopprj.shop.domain.repository.CartRepository;
import shopprj.shop.domain.repository.OrderRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    
    public void cartSave(ItemDto itemDto, CartDto cartDto){
        //원하는 물건 담기
        Cart cart = cartDto.toCart();
        cartRepository.save(cart);
    }

    public void OrderItem(OrderDto orderDto, ItemDto itemDto){
        //중간에 save하는 과정이 필요하다.
        Order order = orderDto.toOrderEntity();
        orderRepository.save(order);
    }

    public void deliveryCancel(OrderDto orderDto){
        Order order = orderDto.toOrderEntity();
        orderRepository.delete(order);
    }


}
