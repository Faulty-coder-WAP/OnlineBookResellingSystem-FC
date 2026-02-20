package OnlineBookResellingSystem.OBRS_BackEnd.book.BookController;

import OnlineBookResellingSystem.OBRS_BackEnd.book.BookDto.bookdetails_dto;
import OnlineBookResellingSystem.OBRS_BackEnd.book.BookDto.responseBookDto;
import OnlineBookResellingSystem.OBRS_BackEnd.book.BookService.serviceimpl.BookServiceImpl;
import OnlineBookResellingSystem.OBRS_BackEnd.security.CustomUserDetails.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.SizeLimitExceededException;
import java.util.List;

@RestController
@RequestMapping("/books")
@Tag(name = "Book Details Api's",description = "Add,Update,retrieve books")
public class BookController
{
    @Autowired
    private BookServiceImpl service;

    @Autowired
    private Validator validator;

    @PostMapping(value = "/addbook",consumes =MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "For Adding New Book For Sale")
    public bookdetails_dto addbook(@AuthenticationPrincipal CustomUserDetails user,@Valid @RequestPart("bookdata") bookdetails_dto bookdata, @RequestPart("image") MultipartFile image)
    {
        return service.addbook(user,bookdata,image);
    }

    @GetMapping("/getbooks")
    @Operation(summary = "To View All Book Listed For Selling")
    public List<responseBookDto> returnAllBooks( @RequestParam(required = false, defaultValue = "4") int pageSize,
                                                 @RequestParam(required = false,defaultValue = "1") int pageNo,
                                                 @RequestParam(required = false,defaultValue = "bookId") String sortBy,
                                                 @RequestParam(required = false,defaultValue = "ASC") String sortDir,
                                                 @RequestParam(required = false) String by)
    {
        if (pageSize>20)
        {
            throw  new IllegalArgumentException("PageSize Can`t Exceeded 20 ");
        }
        Sort sort=null;
        sort= (sortDir.equalsIgnoreCase("ASC"))?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        return service.returnAllBooks("ACTIVE",by,PageRequest.of(pageNo-1,pageSize,sort));
    }

    @PatchMapping("/updatebook")
    @Operation(summary = "Update Details Of Book Listed")
    public bookdetails_dto returnUpdatedBook(@AuthenticationPrincipal CustomUserDetails user, @Valid @RequestBody responseBookDto ubd)
    {
        return service.updateBook(user,ubd);
    }



}
