package OnlineBookResellingSystem.OBRS_BackEnd.auth.AuthService;

import OnlineBookResellingSystem.OBRS_BackEnd.auth.AuthDto.LoginDto;
import OnlineBookResellingSystem.OBRS_BackEnd.auth.JwtClasses.JwtHelper;
import OnlineBookResellingSystem.OBRS_BackEnd.auth.AuthDto.TokenHolder;
import OnlineBookResellingSystem.OBRS_BackEnd.security.CustomUserDetails.CustomUserDetails;
import OnlineBookResellingSystem.OBRS_BackEnd.user.repository.userRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.System.*;

@Service
public class AuthService
{
    @Autowired
    private userRepository repo;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private AuthenticationManager authenticationManager;

    public ResponseEntity<TokenHolder>  returnJwtToken(LoginDto secData)throws BadCredentialsException
    {
        Authentication auth=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(secData.getUsername(),secData.getPassword()));
        CustomUserDetails dataForToken=(CustomUserDetails) auth.getPrincipal();
        Set<String> roles=dataForToken.getAuthorities().stream().map(r->r.getAuthority()).map(r->r.substring(5)).collect(Collectors.toSet());
        TokenHolder tkn=new TokenHolder();
        String jwttkn=jwtHelper.generateToken(dataForToken.getUsername(),roles);
        tkn.setToken(jwttkn);
        return ResponseEntity.ok().body(tkn);

    }
}
