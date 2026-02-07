package OnlineBookResellingSystem.OBRS_BackEnd.exception.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtExceptionDto
{
    private String Reason;
    private int code;
    private LocalDateTime time;
}
