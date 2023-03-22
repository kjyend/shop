package shopprj.shop.dto;

import lombok.Builder;
import lombok.Getter;
import shopprj.shop.domain.entity.Member;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Getter
public class MemberDto {//계정 생성 dto
    private Long id;
    @NotBlank(message = "아이디는 null이 들어올 수 없습니다")
    @Email(message = "이메일 형식을 맞춰주세요.")
    private String loginId;
    @NotBlank(message = "비밀번호는 null이 들어올 수 없습니다")
    @Size(min = 2,max=20, message = "비밀번호를 2-20자 사이로 입력해주세요")
    private String password;
    @Size(min = 2,max = 10,message = "이름을 2-10자 사이로 입력해주세요.")
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
