package shopprj.shop.service.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopprj.shop.domain.entity.Item;
import shopprj.shop.domain.entity.Member;
import shopprj.shop.domain.entity.Order;
import shopprj.shop.domain.entity.OrderItem;
import shopprj.shop.dto.MemberDto;
import shopprj.shop.dto.OrderDto;
import shopprj.shop.dto.OrderItemDto;
import shopprj.shop.repository.item.ItemRepository;
import shopprj.shop.repository.member.MemberRepository;
import shopprj.shop.repository.order.OrderItemRepository;
import shopprj.shop.repository.order.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    private final OrderItemRepository orderItemRepository;

    

    public Long orderSave(OrderDto orderDto, MemberDto memberDto){
        Member member = memberRepository.findById(memberDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("회원이 없습니다."));

        Order order = orderDto.toOrderEntity(member);
        orderRepository.save(order);
        return order.getId();
    }

    public void orderItemSave(Long orderId,Long itemId,Integer stock,Integer price){

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 아이템이 없습니다."));
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 주문이 없습니다."));

        OrderItem orderItem = OrderItem.builder()
                .orderPrice(price)
                .count(stock)
                .order(order)
                .item(item)
                .build();
        orderItemRepository.save(orderItem);
    }

    public List<OrderItemDto> listOrder(Long memberId){
        return orderItemRepository.getOrderItemList(memberId);
    }

    public void orderCancel(Long orderId,Integer stock,Long itemId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문이 없습니다."));

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException("아이템이 없습니다."));

        item.addStock(stock);

        orderRepository.delete(order);


    }
}
