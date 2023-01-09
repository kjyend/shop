package shopprj.shop.domain.dto;

import lombok.Builder;
import lombok.Getter;
import shopprj.shop.domain.entity.Delivery;
import shopprj.shop.domain.entity.Member;
import shopprj.shop.domain.entity.status.DeliveryStatus;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
public class DeliveryDto {

    private Long id;
    @NotBlank
    private String city;
    @NotBlank
    private String street;
    @NotBlank
    private String zipcode;
    private DeliveryStatus status;

    public Delivery toDelivery(){
        return Delivery.builder()
                .city(city)
                .street(street)
                .zipcode(zipcode)
                .status(DeliveryStatus.READY)
                .build();
    }

}
