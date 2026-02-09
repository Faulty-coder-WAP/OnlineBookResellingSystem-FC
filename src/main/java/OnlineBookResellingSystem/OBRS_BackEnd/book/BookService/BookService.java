package OnlineBookResellingSystem.OBRS_BackEnd.book.BookService;

import OnlineBookResellingSystem.OBRS_BackEnd.book.BookDto.bookdetails_dto;
import OnlineBookResellingSystem.OBRS_BackEnd.book.BookDto.responseBookDto;
import OnlineBookResellingSystem.OBRS_BackEnd.book.BookEntity.BooKDetails;
import OnlineBookResellingSystem.OBRS_BackEnd.security.CustomUserDetails.CustomUserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface BookService
{

    bookdetails_dto addbook(CustomUserDetails user, bookdetails_dto bookdata, MultipartFile image);

    List<responseBookDto> returnAllBooks();
}
