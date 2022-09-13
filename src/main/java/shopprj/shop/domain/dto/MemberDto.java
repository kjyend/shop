package shopprj.shop.domain.dto;

import lombok.Builder;
import lombok.Getter;
import shopprj.shop.domain.entity.Member;

@Builder
@Getter
public class MemberDto {
    private String loginId;
    private String password;
    private String name;

    public Member toMemberEntity(MemberDto memberDto){
        return Member.builder()
                .loginId(memberDto.getLoginId())
                .password(memberDto.getPassword())
                .name(memberDto.getName()).build();
    }
}
