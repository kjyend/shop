package shopprj.shop.domain.repository;

import shopprj.shop.domain.dto.CartDto;

import java.util.List;

public interface CartCustomRepository {

    List<CartDto> getLikeList(Long memberId);

}
