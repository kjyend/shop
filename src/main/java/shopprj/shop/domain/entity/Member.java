package shopprj.shop.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shopprj.shop.domain.dto.MemberDto;
import shopprj.shop.domain.entity.status.MemberStatus;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id @GeneratedValue
    private Long id;

    private String loginId;
    private String password;
    private String name;

    @OneToMany(mappedBy="member")
    private List<Comment> comments=new ArrayList<Comment>();

    @OneToMany(mappedBy = "member")
    private List<Cart> carts=new ArrayList<Cart>();

    @OneToMany(mappedBy = "member")
    private List<Order> orders=new ArrayList<Order>();

    @Enumerated(EnumType.STRING)
    private MemberStatus status;//[MEMBER, MANAGER, ADMIN]

    public MemberDto toMemberDto(){
        return MemberDto.builder()
                .id(id)
                .loginId(loginId)
                .password(password)
                .name(name).build();
    }

    public void updateMember(String loginId, String password, String name){
        this.loginId=loginId;
        this.password=password;
        this.name=name;
    }

}
