package shopprj.shop.repository.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopprj.shop.domain.entity.Cart;
import shopprj.shop.repository.cart.CartCustomRepository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>, CartCustomRepository {

}
