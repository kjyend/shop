package shopprj.shop.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopprj.shop.domain.dto.DeliveryDto;
import shopprj.shop.domain.entity.Delivery;
import shopprj.shop.domain.entity.Member;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery,String> {
    List<DeliveryDto> findByMember(Member member);
    //이걸써야하나?
}
