package shopprj.shop.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String loginId;
    private String password;
    //회원 과 운영자를 구분하는
}
