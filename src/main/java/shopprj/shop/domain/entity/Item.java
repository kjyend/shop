package shopprj.shop.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shopprj.shop.domain.dto.ItemDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private Long price;
    private Long stockQuantity;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Cart> cart=new ArrayList<Cart>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems=new ArrayList<OrderItem>();

    public ItemDto toItemDto() {
        return ItemDto.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .orderItems(orderItems)
                .build();
    }

    public void addStock(Long stock){
        this.stockQuantity+=stock;
    }

    public void subtractStock(Long stock){
        this.stockQuantity-=stock;
    }
}
