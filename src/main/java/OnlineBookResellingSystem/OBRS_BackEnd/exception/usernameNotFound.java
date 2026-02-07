package OnlineBookResellingSystem.OBRS_BackEnd.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import java.util.Date;

public class usernameNotFound extends RuntimeException
{
    public usernameNotFound(String e)
    {
        super(e);
    }
}
