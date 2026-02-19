package OnlineBookResellingSystem.OBRS_BackEnd.exception;

import OnlineBookResellingSystem.OBRS_BackEnd.exception.Dto.errorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.ErrorResponse;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandling
{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<errorResponse> handleValidations(MethodArgumentNotValidException ex,HttpServletRequest request)
    {
        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + " " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        errorResponse res=new errorResponse(LocalDateTime.now(),HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(),message,request.getRequestURI());
                return ResponseEntity.badRequest().body(res);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<errorResponse> handleUsernameNotFound(usernameNotFound message, HttpServletRequest request)
    {
     errorResponse res=new errorResponse(LocalDateTime.now(),HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.getReasonPhrase(),message.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<errorResponse>  duplicateEmail(DataIntegrityViolationException message,HttpServletRequest request)
    {
        errorResponse res=new errorResponse(LocalDateTime.now(),HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(),message.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<errorResponse> handlebadCredits(BadCredentialsException msg,HttpServletRequest request)
    {
        errorResponse res=new errorResponse(LocalDateTime.now(),HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.getReasonPhrase(),msg.getMessage(),request.getRequestURI());
       return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<?> handleReqiredPartMissingException(MissingServletRequestPartException msg,HttpServletRequest request)
    {
        errorResponse res=new errorResponse(LocalDateTime.now(),HttpStatus.BAD_REQUEST.value(),"One Of Required Fileds Are missing",msg.getRequestPartName(),request.getRequestURI());
        return ResponseEntity.badRequest().body(res);
    }
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<?> maxSizeException(MaxUploadSizeExceededException msg,HttpServletRequest request)
    {
        errorResponse res=new errorResponse(LocalDateTime.now(),HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(),msg.getMessage(),request.getRequestURI());
        return ResponseEntity.badRequest().body(res);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> notAllowedElementsException(IllegalArgumentException msg,HttpServletRequest request)
    {
        errorResponse res=new errorResponse(LocalDateTime.now(),HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(),msg.getMessage(),request.getRequestURI());
        return ResponseEntity.badRequest().body(res);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> wrongFormatHandler(HttpMessageNotReadableException msg,HttpServletRequest request)
    {
        errorResponse res=new errorResponse(LocalDateTime.now(),HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(),"Requests Fields Are Violated",request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> unAuthorizedResourceAcess(AccessDeniedException msg,HttpServletRequest request)
    {
        errorResponse res=new errorResponse(LocalDateTime.now(),HttpStatus.FORBIDDEN.value(),HttpStatus.FORBIDDEN.getReasonPhrase(),msg.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(res);
    }

    @ExceptionHandler(bookNotFoundException.class)
    public ResponseEntity<errorResponse> handleResourceNotFound(bookNotFoundException msg,HttpServletRequest request)
    {
        errorResponse res=new errorResponse(LocalDateTime.now(),HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.getReasonPhrase(),msg.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(res);
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<errorResponse> handleInvalidApiUsage(InvalidDataAccessApiUsageException msg,HttpServletRequest request)
    {
        errorResponse res=new errorResponse(LocalDateTime.now(),HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(),"Invalid Sort Field",request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<errorResponse> wrongMethodType(HttpRequestMethodNotSupportedException msg,HttpServletRequest request)
    {
        errorResponse res=new errorResponse(LocalDateTime.now(),HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(),msg.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
    }


}
