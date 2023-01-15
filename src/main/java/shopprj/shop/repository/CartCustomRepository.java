package shopprj.shop.repository;

import shopprj.shop.dto.CartDto;

import java.util.List;

public interface CartCustomRepository {

    List<CartDto> getLikeList(Long memberId);

}
