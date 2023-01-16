package shopprj.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopprj.shop.domain.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>,CartCustomRepository {

}
