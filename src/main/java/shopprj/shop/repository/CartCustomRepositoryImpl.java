package shopprj.shop.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import shopprj.shop.dto.CartDto;


import java.util.List;

import static shopprj.shop.domain.entity.QCart.cart;
import static shopprj.shop.domain.entity.QItem.item;
import static shopprj.shop.domain.entity.QMember.member;

@RequiredArgsConstructor
public class CartCustomRepositoryImpl implements CartCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<CartDto> getLikeList(Long memberId) {
        return jpaQueryFactory.select(Projections.constructor(CartDto.class,
                cart.status,cart.item,cart.member))
                .from(cart)//join,cart랑 member랑 조인
                .join(cart.item, item).on(cart.member.id.eq(memberId))
                .fetch();
    }
}
