package shopprj.shop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shopprj.shop.domain.entity.Delivery;
import shopprj.shop.domain.entity.Member;
import shopprj.shop.domain.entity.status.DeliveryStatus;
import shopprj.shop.dto.DeliveryDto;
import shopprj.shop.dto.MemberDto;
import shopprj.shop.repository.DeliveryRepository;
import shopprj.shop.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final MemberRepository memberRepository;


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
