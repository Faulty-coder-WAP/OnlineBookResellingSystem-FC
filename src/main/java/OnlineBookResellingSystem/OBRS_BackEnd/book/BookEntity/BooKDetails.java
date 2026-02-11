package OnlineBookResellingSystem.OBRS_BackEnd.book.BookEntity;

import OnlineBookResellingSystem.OBRS_BackEnd.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.text.DateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "Book_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooKDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  bookId;
    private String bookName;
    @Column(nullable = false,updatable = false)
    @CreationTimestamp
    private Instant postedOn;
    private String authorName;
    private String description;
    private String imgUrl;
    private int price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_user_id")
    private User user;
}
