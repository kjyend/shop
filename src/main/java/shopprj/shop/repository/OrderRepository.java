package shopprj.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopprj.shop.domain.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByMember(String memberId);
}
