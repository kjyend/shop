package shopprj.shop.domain.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class OrderItem {
    @Id @GeneratedValue
    private Long id;

    private Long orderPrice;
    private Long count;
}
