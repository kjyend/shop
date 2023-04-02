package shopprj.shop.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import shopprj.shop.domain.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
