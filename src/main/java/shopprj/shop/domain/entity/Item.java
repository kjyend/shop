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

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems=new ArrayList<OrderItem>();


    public ItemDto toItemDto(Item item) {
        return ItemDto.builder()
                .name(item.getName())
                .price(item.getPrice())
                .stockQuantity(item.getStockQuantity()).build();
    }

    public void addStock(Long stock){
        this.stockQuantity+=stock;
    }
}
