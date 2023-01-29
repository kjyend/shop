package shopprj.shop.dto;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Builder
@Getter
public class ItemCreateDto {

    private Long id;
    @NotNull(message = "null은 안됩니다.")
    private Integer stockQuantity;
}
