package shopprj.shop.domain.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import shopprj.shop.domain.entity.status.OrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue
    private Long id;

    @CreatedDate
    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus Status; //[ORDER, CANCEL]

}
