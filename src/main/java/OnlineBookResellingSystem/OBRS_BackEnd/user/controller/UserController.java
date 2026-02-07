package OnlineBookResellingSystem.OBRS_BackEnd.user.controller;

import OnlineBookResellingSystem.OBRS_BackEnd.security.CustomUserDetails.CustomUserDetails;
import OnlineBookResellingSystem.OBRS_BackEnd.user.dto.UpdateUserDto;
import OnlineBookResellingSystem.OBRS_BackEnd.user.dto.userDto;
import OnlineBookResellingSystem.OBRS_BackEnd.user.entity.User;
import OnlineBookResellingSystem.OBRS_BackEnd.user.service.ServiceImpl.userServiceImpl;
import OnlineBookResellingSystem.OBRS_BackEnd.user.service.userService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController
{
    @Autowired
    private userService service;

    @PostMapping("/register")
    public userDto registerUser(@Valid @RequestBody userDto data)
    {
        return service.registerUser(data);
    }

    @PatchMapping("/update_user/me")
    public String updateUser(@AuthenticationPrincipal CustomUserDetails user, @Valid @RequestBody UpdateUserDto data)
    {
        return service.updateUserByUsername(user.getUserId(),data);
    }

    @GetMapping("/greet")
    public String greet()
    {
     return  "hello";
    }
}
