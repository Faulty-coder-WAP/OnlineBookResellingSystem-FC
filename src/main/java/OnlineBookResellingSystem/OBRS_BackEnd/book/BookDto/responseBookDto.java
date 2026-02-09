package OnlineBookResellingSystem.OBRS_BackEnd.book.BookDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class responseBookDto
{
    private String bookName;
    private String authorName;
    private String postedBy;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",timezone = "Asia/Kolkata")
    private Instant postedOn;
    private String description;
    private int price;
    private String imageUrl;
}
