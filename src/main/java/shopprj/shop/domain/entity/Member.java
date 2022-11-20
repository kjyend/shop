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
    @Column(name = "member_id")
    private Long id;

    private String loginId;
    private String password;
    private String name;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;//[MEMBER, MANAGER, ADMIN]

    @OneToMany(mappedBy="member", cascade = CascadeType.ALL)
    private List<Comment> comments=new ArrayList<Comment>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Cart> carts=new ArrayList<Cart>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Order> orders=new ArrayList<Order>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Delivery> deliveries=new ArrayList<Delivery>();


    public MemberDto toMemberDto(){
        return MemberDto.builder()
                .id(id)
                .loginId(loginId)
                .password(password)
                .name(name)
                .status(MemberStatus.MEMBER).build();
    }

    public void updateMember(String loginId, String password, String name){
        this.loginId=loginId;
        this.password=password;
        this.name=name;
    }

}
