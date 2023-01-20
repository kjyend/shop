package shopprj.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopprj.shop.domain.entity.Cart;
import shopprj.shop.domain.entity.Item;

import java.util.List;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
