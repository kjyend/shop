package shopprj.shop.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class category {
    @Id @GeneratedValue
    private Long id;

    private String categoryName;

}
