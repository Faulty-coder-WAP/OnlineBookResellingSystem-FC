package OnlineBookResellingSystem.OBRS_BackEnd.book.BookController;

import OnlineBookResellingSystem.OBRS_BackEnd.book.BookDto.bookdetails_dto;
import OnlineBookResellingSystem.OBRS_BackEnd.book.BookEntity.BooKDetails;
import OnlineBookResellingSystem.OBRS_BackEnd.book.BookService.BookService;
import OnlineBookResellingSystem.OBRS_BackEnd.book.BookService.serviceimpl.BookServiceImpl;
import OnlineBookResellingSystem.OBRS_BackEnd.security.CustomUserDetails.CustomUserDetails;
import OnlineBookResellingSystem.OBRS_BackEnd.user.entity.User;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

import java.util.Set;

@RestController
@RequestMapping("/books")
public class BookController
{
    @Autowired
    private BookServiceImpl service;

    @Autowired
    private Validator validator;

    @PostMapping(value = "/addbook",consumes =MediaType.MULTIPART_FORM_DATA_VALUE)
    public BooKDetails addbook(@AuthenticationPrincipal String user,@Valid @RequestPart("bookdata") String bookdata, @RequestPart("image") MultipartFile image)
    {
        ObjectMapper mapper = new ObjectMapper();
        bookdetails_dto dto = mapper.readValue(bookdata, bookdetails_dto.class);

        Set<ConstraintViolation<bookdetails_dto>> violations =
                validator.validate(dto);
        if (!violations.isEmpty())
        {
            throw new ConstraintViolationException("One Of The Required Fields Are Empty ",violations);
        }
        return service.addbook(user,dto,image);
    }

    @PostMapping(value = "/testimg",consumes =MediaType.MULTIPART_FORM_DATA_VALUE)
    public String test(@RequestPart MultipartFile image) {
        return image.getContentType();
    }

}
