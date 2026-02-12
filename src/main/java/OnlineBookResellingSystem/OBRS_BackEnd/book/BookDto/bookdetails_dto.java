package OnlineBookResellingSystem.OBRS_BackEnd.book.BookDto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class bookdetails_dto
{
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9]{3,20}$")
    private String bookName;

    @NotBlank
    @Pattern(regexp = "^[A-Za-z]{3,20}$")
    private String authorName;

    @NotBlank
    @Size(min = 3,max = 100)
    private String description;

    @Min(1)
    @Max(10000)
    private int price;

}
