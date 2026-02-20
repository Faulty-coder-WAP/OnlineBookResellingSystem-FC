package OnlineBookResellingSystem.OBRS_BackEnd.user.service.ServiceImpl;

import OnlineBookResellingSystem.OBRS_BackEnd.user.dto.UpdateUserDto;
import OnlineBookResellingSystem.OBRS_BackEnd.user.dto.userDto;
import OnlineBookResellingSystem.OBRS_BackEnd.user.dto.userListDto;
import OnlineBookResellingSystem.OBRS_BackEnd.user.entity.User;
import OnlineBookResellingSystem.OBRS_BackEnd.user.enums.AllowedRoles;
import OnlineBookResellingSystem.OBRS_BackEnd.user.repository.userRepository;
import OnlineBookResellingSystem.OBRS_BackEnd.user.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class userServiceImpl implements userService
{
    @Autowired
    private userRepository repo;
    @Autowired
    private PasswordEncoder encoder;

    @Override
    public ResponseEntity<userDto> registerUser(userDto userdetails)
    {
        User newUSer=new User();
        newUSer.setUserName(userdetails.getUsername());
        newUSer.setEmail(userdetails.getEmail());
        newUSer.setPassword(encoder.encode(userdetails.getPassword()));
        Set<AllowedRoles> user_roles=new HashSet<>();
        user_roles.add(AllowedRoles.USER);
        newUSer.setRoles(user_roles);
        User user= repo.save(newUSer);
        userDto response=new userDto();
        response.setUsername(user.getUserName());
        response.setEmail(user.getEmail());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<String> updateUserByUsername(Long id, UpdateUserDto userdetails)
    {
       User update=repo.findById(id).orElseThrow();
       if (userdetails.getUsername()!=null && !userdetails.getUsername().isBlank())
       {
           update.setUserName(userdetails.getUsername());
       }
        if (userdetails.getPassword()!=null &&!userdetails.getPassword().isBlank())
        {
            update.setPassword(encoder.encode(userdetails.getPassword()));
        }
        if (userdetails.getEmail()!=null &&!userdetails.getEmail().isBlank())
        {
            update.setEmail(userdetails.getEmail());
        }
        repo.save(update);
        return ResponseEntity.status(HttpStatus.OK).body("Details Updated");
    }

    @Override
    public ResponseEntity<List<userListDto>> getAllUsers()
    {
        List<User> data=repo.findAll();
        List<userListDto> response=data
                .stream()
                .map(user->new userListDto(user.getUserName(),user.getEmail()))
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
