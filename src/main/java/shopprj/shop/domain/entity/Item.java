package shopprj.shop.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import shopprj.shop.domain.dto.ItemDto;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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


    private Integer price;


    private Integer stockQuantity;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Cart> cart=new ArrayList<Cart>();

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems=new ArrayList<OrderItem>();

    @OneToMany(mappedBy = "item" ,cascade = CascadeType.ALL)
    private List<Comment> commentList=new ArrayList<Comment>();

    public ItemDto toItemDto() {
        return ItemDto.builder()
                .name(name)
                .price(price)
                .stockQuantity(stockQuantity)
                .orderItems(orderItems)
                .build();
    }

    public void addStock(Integer stock){
        this.stockQuantity+=stock;
    }

    public void subtractStock(Integer stock){
        this.stockQuantity-=stock;
    }
}
