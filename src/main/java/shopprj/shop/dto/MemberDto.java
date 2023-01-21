package shopprj.shop.dto;

import lombok.Builder;
import lombok.Getter;
import shopprj.shop.domain.entity.Member;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
public class MemberDto {//계정 생성 dto
    private Long id;
    @NotBlank(message = "아이디는 null이 들어올 수 없습니다")
    private String loginId;
    @NotBlank(message = "비밀번호는 null이 들어올 수 없습니다")
    private String password;
    private String memberName;

    public Member toMemberEntity(){
        return Member.builder()
                .id(id)
                .loginId(loginId)
                .password(password)
                .memberName(memberName)
                .build();
    }
}
