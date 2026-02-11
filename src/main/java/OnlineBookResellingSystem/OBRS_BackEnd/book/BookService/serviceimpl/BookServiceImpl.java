package OnlineBookResellingSystem.OBRS_BackEnd.book.BookService.serviceimpl;

import OnlineBookResellingSystem.OBRS_BackEnd.book.BookDto.bookdetails_dto;
import OnlineBookResellingSystem.OBRS_BackEnd.book.BookDto.responseBookDto;
import OnlineBookResellingSystem.OBRS_BackEnd.book.BookEntity.BooKDetails;
import OnlineBookResellingSystem.OBRS_BackEnd.book.BookRepository.BookRepository;
import OnlineBookResellingSystem.OBRS_BackEnd.book.BookService.BookService;
import OnlineBookResellingSystem.OBRS_BackEnd.book.BookService.CloudinaryConfig.ClodinaryService;
import OnlineBookResellingSystem.OBRS_BackEnd.security.CustomUserDetails.CustomUserDetails;
import OnlineBookResellingSystem.OBRS_BackEnd.user.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService
{

    @Autowired
    private BookRepository repo;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ClodinaryService clodinaryService;

    @Override
    public bookdetails_dto addbook(CustomUserDetails user, bookdetails_dto bookdata, MultipartFile image)  {

        BooKDetails data=new BooKDetails();
        data.setBookName(bookdata.getBookName());
        data.setAuthorName(bookdata.getAuthorName());
        data.setPrice(bookdata.getPrice());
        data.setDescription(bookdata.getDescription());
        try{
            String link=clodinaryService.uploadImage(image);
            data.setImgUrl(link);
        }
        catch (IOException ex)
        {
            try {
                throw new IOException("Upload Failed");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        Long id= user.getUserId();
        User userdata=entityManager.getReference(User.class,id);
        data.setUser(userdata);
        repo.save(data);
        return bookdata;
    }

    @Override
    public List<responseBookDto> returnAllBooks(String search,Pageable pageable) {

        if (search == null) {
            List<BooKDetails> book = repo.findAll(pageable).getContent();
            List<responseBookDto> response = book.stream()
                    .map(each ->
                            new responseBookDto(each.getBookName(),
                                    each.getAuthorName(),
                                    each.getUser().getUserName(),
                                    each.getPostedOn(),
                                    each.getDescription(),
                                    each.getPrice(),
                                    each.getImgUrl()))
                    .toList();
            return response;
        } else {
            List<BooKDetails> book = repo.findByName(search, pageable).getContent();
            List<responseBookDto> response = book.stream()
                    .map(each ->
                            new responseBookDto(each.getBookName(),
                                    each.getAuthorName(),
                                    each.getUser().getUserName(),
                                    each.getPostedOn(),
                                    each.getDescription(),
                                    each.getPrice(),
                                    each.getImgUrl()))
                    .toList();
            return response;
        }
    }
}


