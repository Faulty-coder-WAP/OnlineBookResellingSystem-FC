package OnlineBookResellingSystem.OBRS_BackEnd.book.BookController;

import OnlineBookResellingSystem.OBRS_BackEnd.book.BookDto.bookdetails_dto;
import OnlineBookResellingSystem.OBRS_BackEnd.book.BookDto.responseBookDto;
import OnlineBookResellingSystem.OBRS_BackEnd.book.BookEntity.BooKDetails;
import OnlineBookResellingSystem.OBRS_BackEnd.book.BookService.BookService;
import OnlineBookResellingSystem.OBRS_BackEnd.book.BookService.serviceimpl.BookServiceImpl;
import OnlineBookResellingSystem.OBRS_BackEnd.security.CustomUserDetails.CustomUserDetails;
import OnlineBookResellingSystem.OBRS_BackEnd.user.entity.User;
import jakarta.annotation.Nullable;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

import java.util.List;
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
    public bookdetails_dto addbook(@AuthenticationPrincipal CustomUserDetails user,@Valid @RequestPart("bookdata") bookdetails_dto bookdata, @RequestPart("image") MultipartFile image)
    {
        return service.addbook(user,bookdata,image);
    }

    @GetMapping("/getbooks")
    public List<responseBookDto> returnAllBooks( @RequestParam(required = false, defaultValue = "4") int pageSize,
                                                 @RequestParam(required = false,defaultValue = "1") int pageNo,
                                                 @RequestParam(required = false,defaultValue = "bookId") String sortBy,
                                                 @RequestParam(required = false,defaultValue = "ASC") String sortDir,
                                                 @RequestParam(required = false) String by)
    {
        Sort sort=null;
        sort= (sortDir.equalsIgnoreCase("ASC"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        return service.returnAllBooks(by,PageRequest.of(pageNo-1,pageSize,sort));
    }



}
