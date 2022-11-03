package shopprj.shop.domain.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    private Long orderPrice;
    private Long count;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name="item_id")
    private Item item;
}
