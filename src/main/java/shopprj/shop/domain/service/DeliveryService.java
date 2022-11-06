package shopprj.shop.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shopprj.shop.domain.dto.DeliveryDto;
import shopprj.shop.domain.dto.MemberDto;
import shopprj.shop.domain.entity.Member;
import shopprj.shop.domain.repository.DeliveryRepository;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryDto findMyDelivery(MemberDto loginMember) {
        Member member = loginMember.toMemberEntity();
        DeliveryDto byMember = deliveryRepository.findByMember(member);
        return byMember;
    }
}
