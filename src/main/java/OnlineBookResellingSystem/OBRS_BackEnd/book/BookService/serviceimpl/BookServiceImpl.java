package OnlineBookResellingSystem.OBRS_BackEnd.book.BookService.serviceimpl;

import OnlineBookResellingSystem.OBRS_BackEnd.book.BookDto.bookdetails_dto;
import OnlineBookResellingSystem.OBRS_BackEnd.book.BookDto.responseBookDto;
import OnlineBookResellingSystem.OBRS_BackEnd.book.BookEntity.BooKDetails;
import OnlineBookResellingSystem.OBRS_BackEnd.book.BookRepository.BookRepository;
import OnlineBookResellingSystem.OBRS_BackEnd.book.BookService.BookService;
import OnlineBookResellingSystem.OBRS_BackEnd.book.BookService.CloudinaryConfig.ClodinaryService;
import OnlineBookResellingSystem.OBRS_BackEnd.book.enums.statusClass;
import OnlineBookResellingSystem.OBRS_BackEnd.exception.bookNotFoundException;
import OnlineBookResellingSystem.OBRS_BackEnd.security.CustomUserDetails.CustomUserDetails;
import OnlineBookResellingSystem.OBRS_BackEnd.user.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

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
    @Transactional
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
        data.setStatus("PENDING");
        repo.save(data);
        return bookdata;
    }

    @Override
    @Transactional
    public List<responseBookDto> returnAllBooks(String status,String search,Pageable pageable) {

        if (search == null) {
            List<BooKDetails> book = repo.findAllByStatus(status,pageable);
            List<responseBookDto> response = book.stream()
                    .map(each ->
                            new responseBookDto(each.getBookId(), each.getBookName(),
                                    each.getAuthorName(),
                                    each.getUser().getUserName(),
                                    each.getPostedOn(),
                                    each.getDescription(),
                                    each.getPrice(),
                                    each.getImgUrl(),
                                    each.getStatus()))
                    .toList();
            return response;
        } else {

            List<BooKDetails> book = repo.findByNameAndStatus(status,search, pageable).getContent();
            List<responseBookDto> response = book.stream()
                    .map(each ->
                            new responseBookDto(each.getBookId(),each.getBookName(),
                                    each.getAuthorName(),
                                    each.getUser().getUserName(),
                                    each.getPostedOn(),
                                    each.getDescription(),
                                    each.getPrice(),
                                    each.getImgUrl(),
                                    each.getStatus()))
                    .toList();
            return response;
        }
    }

    @Override
    public bookdetails_dto updateBook(CustomUserDetails user, responseBookDto updateBookDto)
    {
        BooKDetails newData=repo.findById(updateBookDto.getBookId()).orElseThrow();
        if (Objects.equals(user.getUserId(), newData.getUser().getUser_id()))
         {
                if(updateBookDto.getDescription()!=null && !updateBookDto.getDescription().isBlank())
                {
                  newData.setDescription(updateBookDto.getDescription());
                }

                if(updateBookDto.getBookName()!=null && !updateBookDto.getBookName().isBlank())
                {
                    newData.setBookName(updateBookDto.getBookName());
                }

                if(updateBookDto.getAuthorName()!=null && !updateBookDto.getAuthorName().isBlank())
                {
                    newData.setAuthorName(updateBookDto.getAuthorName());
                }

                if( updateBookDto.getPrice()!=null)
                {
                    newData.setPrice(updateBookDto.getPrice());
                }
                repo.save(newData);
                bookdetails_dto res=new bookdetails_dto();
                res.setBookName(newData.getBookName());
                res.setDescription(newData.getDescription());
                res.setAuthorName(newData.getAuthorName());
                res.setPrice(newData.getPrice());
                return res;
            }
        else
        {
            throw new AccessDeniedException("Your Are Not Allowed To Access This Resource");
        }
    }
}


