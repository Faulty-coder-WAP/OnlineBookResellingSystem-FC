package OnlineBookResellingSystem.OBRS_BackEnd.book.BookService;

import OnlineBookResellingSystem.OBRS_BackEnd.book.BookDto.bookdetails_dto;
import OnlineBookResellingSystem.OBRS_BackEnd.book.BookEntity.BooKDetails;
import OnlineBookResellingSystem.OBRS_BackEnd.security.CustomUserDetails.CustomUserDetails;
import org.springframework.web.multipart.MultipartFile;


public interface BookService
{

    BooKDetails addbook(String user, bookdetails_dto bookdata, MultipartFile image);
}
