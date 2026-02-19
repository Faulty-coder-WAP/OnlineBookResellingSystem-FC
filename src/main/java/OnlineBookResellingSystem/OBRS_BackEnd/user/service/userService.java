package OnlineBookResellingSystem.OBRS_BackEnd.user.service;

import OnlineBookResellingSystem.OBRS_BackEnd.user.dto.UpdateUserDto;
import OnlineBookResellingSystem.OBRS_BackEnd.user.dto.userDto;
import OnlineBookResellingSystem.OBRS_BackEnd.user.dto.userListDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface userService
{

    public ResponseEntity<userDto> registerUser(userDto userdetails);
    public ResponseEntity<String> updateUserByUsername(Long id, UpdateUserDto userdetails);

    ResponseEntity<List<userListDto>> getAllUsers();
}
