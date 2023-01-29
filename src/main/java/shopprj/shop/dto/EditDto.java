package shopprj.shop.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
public class EditDto {

    @NotBlank(message = "아이디는 null이 들어올 수 없습니다")
    private String loginId;
    @NotBlank(message = "비밀번호는 null이 들어올 수 없습니다")
    private String password;
    private String memberName;
}
