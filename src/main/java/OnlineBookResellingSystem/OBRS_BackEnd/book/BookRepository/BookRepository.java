package OnlineBookResellingSystem.OBRS_BackEnd.book.BookRepository;

import OnlineBookResellingSystem.OBRS_BackEnd.book.BookEntity.BooKDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BooKDetails,Long>
{

}
