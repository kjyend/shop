package shopprj.shop.domain.entity;

import lombok.Getter;
import shopprj.shop.domain.entity.status.DeliveryStatus;

import javax.persistence.*;

@Entity
@Getter
public class Delivery {
    @Id @GeneratedValue
    private Long id;

    private String city;
    private String street;
    private String zipcode;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //[READY, COMP, ARRIVAL]
}
