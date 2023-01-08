package shopprj.shop.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shopprj.shop.domain.dto.DeliveryDto;
import shopprj.shop.domain.dto.MemberDto;
import shopprj.shop.domain.entity.Delivery;
import shopprj.shop.domain.entity.Member;
import shopprj.shop.domain.entity.status.DeliveryStatus;
import shopprj.shop.domain.repository.DeliveryRepository;
import shopprj.shop.domain.repository.MemberRepository;

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

    public void saveDelivery(MemberDto memberDto,DeliveryDto deliveryDto){
        Member member = memberRepository.findById(memberDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("멤버가 없습니다."));
        Delivery delivery = Delivery.builder()
                .city(deliveryDto.getCity())
                .street(deliveryDto.getStreet())
                .zipcode(deliveryDto.getZipcode())
                .status(DeliveryStatus.READY)
                .member(member)
                .build();
        deliveryRepository.save(delivery);
    }
}
