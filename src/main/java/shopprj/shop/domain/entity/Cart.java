package shopprj.shop.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cart {
    @Id @GeneratedValue
    private String id;

    private Long cartCount;
}
