package shopprj.shop.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopprj.shop.domain.entity.Delivery;
import shopprj.shop.domain.entity.Member;
import shopprj.shop.dto.DeliveryDto;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery,Long> {
    List<DeliveryDto> findByMember(Member member);
    //이걸써야하나?
}
