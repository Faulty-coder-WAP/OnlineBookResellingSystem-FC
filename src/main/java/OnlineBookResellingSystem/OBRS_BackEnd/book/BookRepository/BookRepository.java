package OnlineBookResellingSystem.OBRS_BackEnd.book.BookRepository;

import OnlineBookResellingSystem.OBRS_BackEnd.book.BookDto.responseBookDto;
import OnlineBookResellingSystem.OBRS_BackEnd.book.BookEntity.BooKDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.net.ContentHandler;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<BooKDetails,Long>
{
    @Query("SELECT b FROM BooKDetails b " +
            "WHERE (LOWER(b.bookName) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "OR LOWER(b.authorName) LIKE LOWER(CONCAT('%', :search, '%'))) " +
            "AND LOWER(b.status) = LOWER(:status)")
        Page<BooKDetails> findByNameAndStatus(@Param("status") String status,@Param("search") String name, Pageable pageable);

    List<BooKDetails> findAllByStatus(String status, Pageable pageable);

    List<BooKDetails> findByStatus(String status);
}
