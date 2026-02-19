package OnlineBookResellingSystem.OBRS_BackEnd.auth.AuthController;

import OnlineBookResellingSystem.OBRS_BackEnd.auth.AuthDto.LoginDto;
import OnlineBookResellingSystem.OBRS_BackEnd.auth.AuthService.AuthService;
import OnlineBookResellingSystem.OBRS_BackEnd.auth.AuthDto.TokenHolder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication Api's",description = "Login manager for user and admin")
public class AuthController
{
    @Autowired
    private AuthService service;

    @PostMapping("/login")
    @Operation(summary = "Login For Authentication")
    public ResponseEntity<TokenHolder> getToken(@Valid @RequestBody LoginDto secData)
    {
      return service.returnJwtToken(secData);
    }
}
