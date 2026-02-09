package OnlineBookResellingSystem.OBRS_BackEnd.exception;

import OnlineBookResellingSystem.OBRS_BackEnd.exception.Dto.JwtExceptionDto;
import OnlineBookResellingSystem.OBRS_BackEnd.exception.Dto.SqlIntergrityviolationDto;
import OnlineBookResellingSystem.OBRS_BackEnd.exception.Dto.UsernameNotFoundDto;
import io.jsonwebtoken.JwtException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.security.SignatureException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandling
{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidations(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors().
                forEach(e -> errors
                        .put(e.getField(),e.getDefaultMessage()));
                return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<UsernameNotFoundDto> handleUsernameNotFound(usernameNotFound message)
    {
      UsernameNotFoundDto res=new UsernameNotFoundDto(message.getMessage(),HttpStatus.NOT_FOUND.value(),LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<SqlIntergrityviolationDto>  duplicateEmail(DataIntegrityViolationException message)
    {
        SqlIntergrityviolationDto error=new SqlIntergrityviolationDto("Email Already Linked To Another User",HttpStatus.BAD_REQUEST.value(),LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<UsernameNotFoundDto> handlebadCredits(BadCredentialsException msg)
    {
        UsernameNotFoundDto error_res=new UsernameNotFoundDto("Username or Password is incorrect",HttpStatus.FORBIDDEN.value(),LocalDateTime.now());
       return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error_res);
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<?> handleReqiredPartMissingException(MissingServletRequestPartException msg)
    {
        Map<String,String> response=new HashMap<>();
        response.put("Error: ","One Of The Required Fileds Are Missing");
        response.put("Fields ",msg.getRequestPartName());
        return ResponseEntity.badRequest().body(response);
    }
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<?> maxSizeException(MaxUploadSizeExceededException msg)
    {
        Map<String,String> res=new HashMap<>();
        res.put("Error ",msg.getMessage());
        return ResponseEntity.badRequest().body(res);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> notAllowedElementsException(IllegalArgumentException msg)
    {
        Map<String,String> response=new HashMap<>();
        response.put("error ",msg.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

}
