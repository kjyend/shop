package shopprj.shop.domain.entity;

import lombok.*;
import shopprj.shop.domain.entity.status.OrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //[ORDER, CANCEL]

    @OneToMany(mappedBy = "order" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderItem> orderItems=new ArrayList<OrderItem>();

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member; //주문 회원

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Builder
    public Order(Long id,LocalDateTime createdDate, OrderStatus status, Member member, Delivery delivery) {
        this.id = id;
        this.createdDate = createdDate;
        this.status = status;
        this.member = member;
        this.delivery = delivery;
    }


}
