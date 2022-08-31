package shopprj.shop.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopprj.shop.domain.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
}
