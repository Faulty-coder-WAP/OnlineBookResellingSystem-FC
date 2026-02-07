package OnlineBookResellingSystem.OBRS_BackEnd.user.service;

import OnlineBookResellingSystem.OBRS_BackEnd.user.dto.UpdateUserDto;
import OnlineBookResellingSystem.OBRS_BackEnd.user.dto.userDto;
import org.springframework.stereotype.Service;


public interface userService
{

    public userDto registerUser(userDto userdetails);
    public String updateUserByUsername(Long id, UpdateUserDto userdetails);

}
