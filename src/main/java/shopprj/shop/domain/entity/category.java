package shopprj.shop.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class category {
    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String categoryName;

}
