package OnlineBookResellingSystem.OBRS_BackEnd.admin.Controller;

import OnlineBookResellingSystem.OBRS_BackEnd.admin.Service.adminService;
import OnlineBookResellingSystem.OBRS_BackEnd.book.BookDto.responseBookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class adminController
{
    @Autowired
    private adminService  service;

    @GetMapping("/getbooks")
    public List<responseBookDto> returnAllBooks()
    {
        return service.returnAllBooks();
    }

    @PatchMapping("/update_book/{id}")
    public String changeBookStatus(@PathVariable Long id)
    {
      return service.updateBook("ACTIVE",id);
    }

}
