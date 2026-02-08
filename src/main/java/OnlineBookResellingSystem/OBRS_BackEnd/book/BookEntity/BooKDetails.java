package OnlineBookResellingSystem.OBRS_BackEnd.book.BookEntity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Book_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooKDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  book_id;
    private String book_name;
    private String posted_by;
    private LocalDateTime posted_on;
    private String author_name;
    private String description;
    private String img_url;
    private int price;
}
