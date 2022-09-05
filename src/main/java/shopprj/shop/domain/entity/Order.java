package shopprj.shop.domain.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private LocalDateTime localDateTime;
    private OrderStatus Status;

}
