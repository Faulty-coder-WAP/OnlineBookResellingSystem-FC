package OnlineBookResellingSystem.OBRS_BackEnd.admin.Service.impl;

import OnlineBookResellingSystem.OBRS_BackEnd.admin.Service.adminService;
import OnlineBookResellingSystem.OBRS_BackEnd.book.BookDto.responseBookDto;
import OnlineBookResellingSystem.OBRS_BackEnd.book.BookEntity.BooKDetails;
import OnlineBookResellingSystem.OBRS_BackEnd.book.BookRepository.BookRepository;
import OnlineBookResellingSystem.OBRS_BackEnd.exception.bookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class adminServiceImpl implements adminService
{
    @Autowired
    private BookRepository repo;

    @Override
    @Transactional
    public List<responseBookDto> returnAllBooks()
    {
        List<responseBookDto> response=repo.findByStatus("PENDING")
                .stream()
                .map(each->new responseBookDto(each.getBookId(),
                        each.getBookName(),
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

    @Override
    public String updateBook(String active, Long id)
    {
        BooKDetails temp=repo.findById(id).orElseThrow(()->new bookNotFoundException("Book With Id "+id+" Doesn't Exist"));
        temp.setStatus(active);
        repo.save(temp);
        return "Changes Updated";
    }
}
