package shopprj.shop.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopprj.shop.domain.entity.Item;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
