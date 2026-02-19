package OnlineBookResellingSystem.OBRS_BackEnd.admin.Service;

import OnlineBookResellingSystem.OBRS_BackEnd.book.BookDto.responseBookDto;

import java.util.List;

public interface adminService
{
    List<responseBookDto> returnAllBooks();

    String updateBook(String active,Long id);
}
