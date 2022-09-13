package shopprj.shop.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shopprj.shop.domain.dto.MemberDto;
import shopprj.shop.domain.entity.status.MemberStatus;

import javax.persistence.*;

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

    @Enumerated(EnumType.STRING)
    private MemberStatus status;//[MEMBER, MANAGER, ADMIN]

    public MemberDto toMemberDto(Member member){
        return MemberDto.builder()
                .loginId(member.getLoginId())
                .password(member.getPassword())
                .name(member.getName()).build();
    }

}
