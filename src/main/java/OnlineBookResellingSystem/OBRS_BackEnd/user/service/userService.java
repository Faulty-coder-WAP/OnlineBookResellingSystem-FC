package OnlineBookResellingSystem.OBRS_BackEnd.user.service;

import OnlineBookResellingSystem.OBRS_BackEnd.user.dto.UpdateUserDto;
import OnlineBookResellingSystem.OBRS_BackEnd.user.dto.userDto;
import OnlineBookResellingSystem.OBRS_BackEnd.user.dto.userListDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface userService
{

    public userDto registerUser(userDto userdetails);
    public String updateUserByUsername(Long id, UpdateUserDto userdetails);

    List<userListDto> getAllUsers();
}
