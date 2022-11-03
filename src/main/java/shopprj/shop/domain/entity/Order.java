package shopprj.shop.domain.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import shopprj.shop.domain.entity.status.OrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @CreatedDate
    private LocalDateTime createdDate;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems=new ArrayList<OrderItem>();

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member; //주문 회원

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    private OrderStatus Status; //[ORDER, CANCEL]

}
