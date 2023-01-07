package shopprj.shop.domain.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import shopprj.shop.domain.dto.OrderDto;
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

    @CreatedDate
    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //[ORDER, CANCEL]

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems=new ArrayList<OrderItem>();

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member; //주문 회원

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Builder
    public Order(Long id,LocalDateTime createdDate, OrderStatus status, List<OrderItem> orderItems, Member member, Delivery delivery) {
        this.id = id;
        this.createdDate = createdDate;
        this.status = status;
        this.orderItems = orderItems;
        this.member = member;
        this.delivery = delivery;
    }

    public OrderDto toOrderDto(){
        return OrderDto.builder()
                .createdDate(createdDate)
                .member(member)
                .status(status).build();
    }
}
