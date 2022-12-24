package shopprj.shop.domain.dto;

import lombok.Builder;
import lombok.Getter;
import shopprj.shop.domain.entity.Member;
import shopprj.shop.domain.entity.status.MemberStatus;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
public class MemberDto {
    private Long id;
    @NotBlank
    private String loginId;
    @NotBlank
    private String password;
    private String name;
    private MemberStatus status;

    public Member toMemberEntity(){
        return Member.builder()
                .id(id)
                .loginId(loginId)
                .password(password)
                .name(name)
                .status(MemberStatus.MEMBER).build();
    }
}
