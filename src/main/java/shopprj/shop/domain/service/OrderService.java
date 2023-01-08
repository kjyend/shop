package shopprj.shop.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shopprj.shop.domain.dto.CartDto;
import shopprj.shop.domain.dto.ItemDto;
import shopprj.shop.domain.dto.MemberDto;
import shopprj.shop.domain.dto.OrderDto;
import shopprj.shop.domain.entity.Cart;
import shopprj.shop.domain.entity.Item;
import shopprj.shop.domain.entity.Member;
import shopprj.shop.domain.entity.Order;
import shopprj.shop.domain.entity.status.CartStatus;
import shopprj.shop.domain.repository.CartRepository;
import shopprj.shop.domain.repository.ItemRepository;
import shopprj.shop.domain.repository.MemberRepository;
import shopprj.shop.domain.repository.OrderRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    
    public void cartSave(MemberDto memberDto,Long itemId){
        //원하는 물건 담기
        Member member = memberRepository.findById(memberDto.getId())
                .orElseThrow(() -> new IllegalArgumentException());

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new IllegalArgumentException());

        Cart cart = Cart.builder()
                .status(CartStatus.LIKE)
                .item(item)
                .member(member)
                .build();
        cartRepository.save(cart);
    }

    public void orderSave(OrderDto orderDto, Long itemId){
        //중간에 save하는 과정이 필요하다.
        Order order = orderDto.toOrderEntity();
//        Order.builder() //시간값을 따로 지정해야한다.
//                .status()
//                .member()
//                .delivery()
//                .build();
        orderRepository.save(order);
    }

    public void orderItemSave(){

    }

    public void orderCancel(OrderDto orderDto){
        Order order = orderDto.toOrderEntity();
        orderRepository.delete(order);
    }


}
