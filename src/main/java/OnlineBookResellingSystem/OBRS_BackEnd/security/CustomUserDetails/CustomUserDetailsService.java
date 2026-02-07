package OnlineBookResellingSystem.OBRS_BackEnd.security.CustomUserDetails;

import OnlineBookResellingSystem.OBRS_BackEnd.user.entity.User;
import OnlineBookResellingSystem.OBRS_BackEnd.user.repository.userRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService
{
    @Autowired
    private userRepository repo;
    @Override
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException
    {
        User data=repo.findByuserName(username);
        if (data==null||data.getUserName().isEmpty())
        {
            throw new UsernameNotFoundException("User Not Found , Please Register");
        }
        return new CustomUserDetails(data);
    }
}
