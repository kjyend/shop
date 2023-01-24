package shopprj.shop.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shopprj.shop.domain.entity.status.DeliveryStatus;
import shopprj.shop.dto.DeliveryDto;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    private String city;
    private String street;
    private String zipcode;


    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //[READY, COMP]

    @Builder
    public Delivery(String city, String street, String zipcode, Member member, DeliveryStatus status) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
        this.member = member;
        this.status = status;
    }

    public DeliveryDto toDeliveryDto(){
        return DeliveryDto.builder()
                .city(city)
                .street(street)
                .zipcode(zipcode)
                .build();
    }
}
