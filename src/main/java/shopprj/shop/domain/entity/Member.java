package shopprj.shop.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shopprj.shop.domain.entity.status.MemberStatus;
import shopprj.shop.dto.EditDto;
import shopprj.shop.dto.MemberDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String loginId;

    private String password;

    private String memberName;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;//[MEMBER, MANAGER, ADMIN]

    @OneToMany(mappedBy="member" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments=new ArrayList<Comment>();

    @OneToMany(mappedBy = "member" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Cart> carts=new ArrayList<Cart>();

    @OneToMany(mappedBy = "member" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Order> orders=new ArrayList<Order>();

    @OneToMany(mappedBy = "member" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Delivery> deliveries=new ArrayList<Delivery>();

    @Builder
    public Member(Long id, String loginId, String password, String memberName, MemberStatus status) {
        this.id=id;
        this.loginId = loginId;
        this.password = password;
        this.memberName = memberName;
        this.status = status;
    }

    public MemberDto toMemberDto(){
        return MemberDto.builder()
                .id(id)
                .loginId(loginId)
                .password(password)
                .memberName(memberName)
                .build();
    }

    public EditDto toLoginDto(){
        return EditDto.builder()
                .loginId(loginId)
                .password(password)
                .build();
    }

    public void edit(String loginId, String password, String name) {
        this.loginId=loginId;
        this.password=password;
        this.memberName=name;
    }
}
