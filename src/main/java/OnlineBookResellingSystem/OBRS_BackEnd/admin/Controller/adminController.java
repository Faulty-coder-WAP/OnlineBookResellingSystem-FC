package OnlineBookResellingSystem.OBRS_BackEnd.admin.Controller;

import OnlineBookResellingSystem.OBRS_BackEnd.admin.Service.adminService;
import OnlineBookResellingSystem.OBRS_BackEnd.book.BookDto.responseBookDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Tag(name = "Admin Api's",description = "Only For People With Admin Role")
public class adminController
{
    @Autowired
    private adminService  service;

    @GetMapping("/getbooks")
    @Operation(summary = "For Admins To View All Users And Manage")
    public List<responseBookDto> returnAllBooks()
    {
        return service.returnAllBooks();
    }

    @PatchMapping("/update_book/{id}")
    @Operation(summary = "For Admins To Accept The Listing Of Book For Users")
    public String changeBookStatus(@PathVariable Long id)
    {
      return service.updateBook("ACTIVE",id);
    }

}
