package shopprj.shop.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shopprj.shop.domain.entity.Item;
import shopprj.shop.domain.entity.Member;
import shopprj.shop.dto.ItemDto;
import shopprj.shop.dto.MemberDto;
import shopprj.shop.repository.OrderRepository;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderService orderService;

    @AfterEach
    void clean(){
        orderRepository.deleteAll();
    }

    @Test
    @DisplayName("order delete 하기")
    void test1(){
        MemberDto memberDto = MemberDto.builder()
                .id(1L)
                .loginId("안녕")
                .password("잘가")
                .memberName("뭔데")
                .build();

        ItemDto itemDto = ItemDto.builder()
                .id(2L)
                .itemName("bool")
                .price(1000)
                .stockQuantity(10)
                .build();

        Member member = memberDto.toMemberEntity();
        Item item = itemDto.toItemEntity();

//        memberRepository.save(member);
//        itemRepository.save(item);
        //item-orderitem-order-member
    }

}