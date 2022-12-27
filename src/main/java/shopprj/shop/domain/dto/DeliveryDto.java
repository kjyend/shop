package shopprj.shop.domain.dto;

import lombok.Builder;
import lombok.Getter;
import shopprj.shop.domain.entity.Delivery;
import shopprj.shop.domain.entity.Member;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
public class DeliveryDto {

    @NotBlank
    private String city;
    @NotBlank
    private String street;
    @NotBlank
    private String zipcode;
    private Member member;

    public Delivery toDelivery(){
        return Delivery.builder()
                .city(city)
                .street(street)
                .zipcode(zipcode)
                .build();
    }

}
