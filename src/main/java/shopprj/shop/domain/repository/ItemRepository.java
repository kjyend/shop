package shopprj.shop.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopprj.shop.domain.entity.Item;

import java.util.List;


@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
     Item findByName(String name);
     // findbycart에서 에러가 나온다. 답이 없는데?

     List<Item> findItemByCart();
}
