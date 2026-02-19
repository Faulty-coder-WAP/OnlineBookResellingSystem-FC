package OnlineBookResellingSystem.OBRS_BackEnd.book.BookDto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class responseBookDto
{
    private Long bookId;
    @Pattern(regexp = "^[A-Za-z0-9]{3,}$")
    private String bookName;

    @Pattern(regexp = "^[A-Za-z]{3,}$")
    private String authorName;
    private String postedBy;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss",timezone = "Asia/Kolkata")
    private Instant postedOn;
    @Size(min = 3,max = 100)
    private String description;

    @Min(1)
    @Max(10000)
    private Integer price;
    private String imageUrl;
    private String status;
}
