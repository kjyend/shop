package shopprj.shop.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shopprj.shop.domain.dto.DeliveryDto;
import shopprj.shop.domain.dto.MemberDto;
import shopprj.shop.domain.entity.Member;
import shopprj.shop.domain.repository.DeliveryRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public Optional<DeliveryDto> findMyDelivery(MemberDto loginMember) {
        Member member = loginMember.toMemberEntity();
        Optional<DeliveryDto> byMember = deliveryRepository.findByMember(member);
        return byMember;
        //delivery에 오류가 나온다. 자신의 번호랑 같은지 확인을 한다.
    }
}
