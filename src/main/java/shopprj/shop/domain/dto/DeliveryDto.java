package shopprj.shop.domain.dto;

import lombok.Builder;
import lombok.Getter;
import shopprj.shop.domain.entity.Delivery;
import shopprj.shop.domain.entity.Member;

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
