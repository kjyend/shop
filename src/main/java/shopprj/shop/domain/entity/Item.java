package shopprj.shop.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shopprj.shop.dto.ItemDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {
    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String itemName;

    private Integer price;

    private Integer stockQuantity;

    @OneToMany(mappedBy = "item" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Cart> cart=new ArrayList<Cart>();

    @OneToMany(mappedBy = "item" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderItem> orderItems=new ArrayList<OrderItem>();

    @OneToMany(mappedBy = "item" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> commentList=new ArrayList<Comment>();

    @Builder
    public Item(Long id, String itemName, Integer price, Integer stockQuantity) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public ItemDto toItemDto() {
        return ItemDto.builder()
                .id(id)
                .itemName(itemName)
                .price(price)
                .stockQuantity(stockQuantity)
                .build();
    }

    public void addStock(Integer stock){
        this.stockQuantity+=stock;
    }

    public void subtractStock(Integer stock){
        this.stockQuantity-=stock;// 0이하로 떨어지는것을 어떻게 막을것인가?
    }
}
