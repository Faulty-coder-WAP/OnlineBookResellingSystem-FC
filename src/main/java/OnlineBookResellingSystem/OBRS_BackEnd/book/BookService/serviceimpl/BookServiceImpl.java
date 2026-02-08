package OnlineBookResellingSystem.OBRS_BackEnd.book.BookService.serviceimpl;

import OnlineBookResellingSystem.OBRS_BackEnd.book.BookDto.bookdetails_dto;
import OnlineBookResellingSystem.OBRS_BackEnd.book.BookEntity.BooKDetails;
import OnlineBookResellingSystem.OBRS_BackEnd.book.BookRepository.BookRepository;
import OnlineBookResellingSystem.OBRS_BackEnd.book.BookService.BookService;
import OnlineBookResellingSystem.OBRS_BackEnd.book.BookService.CloudinaryConfig.ClodinaryService;
import OnlineBookResellingSystem.OBRS_BackEnd.security.CustomUserDetails.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class BookServiceImpl implements BookService
{

    @Autowired
    private BookRepository repo;

    @Autowired
    private ClodinaryService clodinaryService;

    @Override
    public BooKDetails addbook(String user, bookdetails_dto bookdata, MultipartFile image)  {

        BooKDetails data=new BooKDetails();
        data.setBook_name(bookdata.getBook_name());
        data.setAuthor_name(bookdata.getAuthor_name());
        data.setPrice(bookdata.getPrice());
        data.setDescription(bookdata.getDescription());
        data.setPosted_by(user);
        data.setPosted_on(LocalDateTime.now());
        try{
            String link=clodinaryService.uploadImage(image);
            data.setImg_url(link);
        }
        catch (IOException ex)
        {
            try {
                throw new IOException("Upload Failed");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return repo.save(data);
    }
}
