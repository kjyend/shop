package shopprj.shop.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shopprj.shop.domain.dto.MemberDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
    private MemberStatus status;

    public MemberDto toMemberDto(Member member){
        return MemberDto.builder()
                .loginId(member.getLoginId())
                .password(member.getPassword()).build();
    }

}
