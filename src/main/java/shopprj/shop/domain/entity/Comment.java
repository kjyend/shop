package shopprj.shop.domain.entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Comment {
    @Id @GeneratedValue
    private Long id;

    private String userName;
    private Long point;
    private String talk;
}
