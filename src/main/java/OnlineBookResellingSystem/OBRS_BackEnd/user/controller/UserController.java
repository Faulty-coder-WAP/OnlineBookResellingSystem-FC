package OnlineBookResellingSystem.OBRS_BackEnd.user.controller;

import OnlineBookResellingSystem.OBRS_BackEnd.security.CustomUserDetails.CustomUserDetails;
import OnlineBookResellingSystem.OBRS_BackEnd.user.dto.UpdateUserDto;
import OnlineBookResellingSystem.OBRS_BackEnd.user.dto.userDto;
import OnlineBookResellingSystem.OBRS_BackEnd.user.dto.userListDto;
import OnlineBookResellingSystem.OBRS_BackEnd.user.entity.User;
import OnlineBookResellingSystem.OBRS_BackEnd.user.service.ServiceImpl.userServiceImpl;
import OnlineBookResellingSystem.OBRS_BackEnd.user.service.userService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "User Details Api's",description = "Register,Update,retrieve(ADMINS ONLY) users")
public class UserController
{
    @Autowired
    private userService service;

    @PostMapping("/register")
    @Operation(summary = "User Registration")
    public ResponseEntity<userDto> registerUser(@Valid @RequestBody userDto data)
    {
        return service.registerUser(data);
    }

    @PatchMapping("/update_user/me")
    @Operation(summary = "User Details Update")
    public ResponseEntity<String> updateUser(@AuthenticationPrincipal CustomUserDetails user, @Valid @RequestBody UpdateUserDto data)
    {
        return service.updateUserByUsername(user.getUserId(),data);
    }

    @GetMapping("/get_users")
    @Operation(summary = "Admin Only Access For Getting All Registered User")
    public ResponseEntity<List<userListDto>> getAllUsers()
    {
        return service.getAllUsers();
    }

    @GetMapping("/greet")
    @Operation(summary ="Test The Application")
    public String greet()
    {
     return  "hello";
    }
}
