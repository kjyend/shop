package shopprj.shop.repository.cart;

import shopprj.shop.dto.CartDto;

import java.util.List;

public interface CartCustomRepository {

    List<CartDto> getLikeList(Long memberId);

}
