package shopprj.shop.service;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import shopprj.shop.domain.entity.Item;
import shopprj.shop.domain.entity.Member;
import shopprj.shop.dto.CartDto;
import shopprj.shop.dto.ItemDto;
import shopprj.shop.dto.MemberDto;
import shopprj.shop.repository.cart.CartRepository;
import shopprj.shop.repository.item.ItemRepository;
import shopprj.shop.repository.member.MemberRepository;
import shopprj.shop.service.cart.CartService;

import java.util.List;

@Slf4j
@SpringBootTest
class CartServiceTest {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CartService cartService;

    @BeforeEach
    void clean(){
        cartRepository.deleteAll();
    }

    @Test
    @DisplayName("cart 저장하기")
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

        memberRepository.save(member);
        itemRepository.save(item);

        cartService.cartSave(memberDto,itemDto.getId());

        Assertions.assertThat(cartRepository.count()).isEqualTo(1L);
        Assertions.assertThat(memberRepository.count()).isEqualTo(1L);

    }

    @Test
    @DisplayName("라이크 member 확인하기")
    void test2(){//member와 여러개의 저장을 한다. 그리고 like 확인

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

        memberRepository.save(member);
        itemRepository.save(item);

        cartService.cartSave(memberDto,itemDto.getId());

        List<CartDto> likeList = cartRepository.getLikeList(member.getId());

        log.info("likeList.id={}",likeList.get(0).getId());
        log.info("likeList.status={}",likeList.get(0).getStatus());
        log.info("likeList.member={}",likeList.get(0).getMember().getId());


    }

    @Test
    @DisplayName("cart 취소하기")
    void test3(){

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

        memberRepository.save(member);
        itemRepository.save(item);

        cartService.cartSave(memberDto,itemDto.getId());

//        cartService.cartCancel();
    }

}