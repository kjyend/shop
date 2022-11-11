package shopprj.shop.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shopprj.shop.domain.dto.DeliveryDto;
import shopprj.shop.domain.entity.status.DeliveryStatus;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    private String city;
    private String street;
    private String zipcode;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //[READY, COMP, ARRIVAL]

    public DeliveryDto toDeliveryDto(){
        return DeliveryDto.builder()
                .city(city)
                .street(street)
                .zipcode(zipcode)
                .build();
    }
}
