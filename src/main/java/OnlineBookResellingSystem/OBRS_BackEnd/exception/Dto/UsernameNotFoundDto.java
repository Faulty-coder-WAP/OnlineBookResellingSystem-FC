package OnlineBookResellingSystem.OBRS_BackEnd.exception.Dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UsernameNotFoundDto
{
    private String message;
    private int status;
    @JsonFormat(pattern ="dd-MM-yyyy HH:mm:ss",timezone = "Asia/kolkata")
    private Instant dateAndTime;
}
