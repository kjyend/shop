package shopprj.shop.domain.entity;

import lombok.Getter;
import shopprj.shop.domain.entity.status.DeliveryStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Delivery {
    @Id @GeneratedValue
    private Long id;

    private String city;
    private String street;
    private String zipcode;

    private DeliveryStatus status;
}
