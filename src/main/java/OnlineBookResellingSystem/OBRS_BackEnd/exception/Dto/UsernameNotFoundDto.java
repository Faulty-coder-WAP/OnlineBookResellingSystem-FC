package OnlineBookResellingSystem.OBRS_BackEnd.exception.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UsernameNotFoundDto
{
    private String message;
    private int status;
    private LocalDateTime dateAndTime;
}
