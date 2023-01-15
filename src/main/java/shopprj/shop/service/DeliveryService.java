package shopprj.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shopprj.shop.dto.DeliveryDto;
import shopprj.shop.dto.MemberDto;
import shopprj.shop.domain.entity.Delivery;
import shopprj.shop.domain.entity.Member;
import shopprj.shop.domain.entity.status.DeliveryStatus;
import shopprj.shop.repository.DeliveryRepository;
import shopprj.shop.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final MemberRepository memberRepository;

    public List<DeliveryDto> findMyDelivery(MemberDto loginMember) {
        Member member = loginMember.toMemberEntity();
        List<DeliveryDto> byMember = deliveryRepository.findByMember(member);
        return byMember;
        //delivery에 오류가 나온다. 자신의 번호랑 같은지 확인을 한다.
        //member로 바꾸고 해야하나?
    }

    public Long saveDelivery(MemberDto memberDto,DeliveryDto deliveryDto){
        Member member = memberRepository.findById(memberDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("멤버가 없습니다."));
        Delivery delivery = Delivery.builder()
                .city(deliveryDto.getCity())
                .street(deliveryDto.getStreet())
                .zipcode(deliveryDto.getZipcode())
                .status(DeliveryStatus.READY)
                .member(member)
                .build();
        Delivery deliveryCheck = deliveryRepository.save(delivery);
        return deliveryCheck.getId();
    }
}
