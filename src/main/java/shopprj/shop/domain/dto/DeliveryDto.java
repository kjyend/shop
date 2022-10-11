package shopprj.shop.domain.dto;

import lombok.Builder;
import lombok.Getter;
import shopprj.shop.domain.entity.Delivery;

@Builder
@Getter
public class DeliveryDto {

    private String city;
    private String street;
    private String zipcode;

    public Delivery toDelivery(){
        return Delivery.builder()
                .city(city)
                .street(street)
                .zipcode(zipcode).build();
    }

}
