package shopprj.shop.dto;

import lombok.Builder;
import lombok.Getter;
import shopprj.shop.domain.entity.Delivery;
import shopprj.shop.domain.entity.status.DeliveryStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Getter
public class DeliveryDto {

    private Long id;
    @NotBlank
    @Size(min = 2,max = 10)
    private String city;
    @NotBlank
    @Size(min = 2,max = 10)
    private String street;
    @NotBlank
    @Size(min = 2,max = 10)
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
