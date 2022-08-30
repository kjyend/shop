package shopprj.shop.domain.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Table(name = "orders")
public class Order {
    @Id @GeneratedValue
    private Long id;

}
