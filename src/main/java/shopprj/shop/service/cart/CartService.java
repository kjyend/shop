package shopprj.shop.service.cart;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shopprj.shop.domain.entity.Cart;
import shopprj.shop.domain.entity.Item;
import shopprj.shop.domain.entity.Member;
import shopprj.shop.domain.entity.status.CartStatus;
import shopprj.shop.dto.CartDto;
import shopprj.shop.dto.MemberDto;
import shopprj.shop.repository.cart.CartRepository;
import shopprj.shop.repository.item.ItemRepository;
import shopprj.shop.repository.member.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final MemberRepository memberRepository;

    private final ItemRepository itemRepository;

    private final CartRepository cartRepository;

    public void cartSave(MemberDto memberDto, Long itemId){
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

    public boolean cartCheck(Long memberId,Long itemId){
        List<CartDto> list = cartRepository.getLikeList(memberId);
        return list.stream().anyMatch(cartDto -> cartDto.getItem().getId().equals(itemId));
    }

    public List<CartDto> cartList(Long memberId){
        return cartRepository.getLikeList(memberId);
    }

    public void cartCancel(Long cartId){
        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("좋아요가 없습니다."));

        cartRepository.delete(cart);
    }
}
