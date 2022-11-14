package shopprj.shop.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopprj.shop.domain.entity.Cart;
import shopprj.shop.domain.entity.Item;

import java.util.List;
import java.util.Optional;


@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
     Item findByName(String name);

     List<Item> findByCart(Cart cart);
}
