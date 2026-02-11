package OnlineBookResellingSystem.OBRS_BackEnd.security.CustomUserDetails;

import OnlineBookResellingSystem.OBRS_BackEnd.user.entity.User;
import OnlineBookResellingSystem.OBRS_BackEnd.user.enums.AllowedRoles;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails
{
    private final Long id;
    private final String email;
    private final Collection<? extends GrantedAuthority> roles;
    private final String password;
    private final String userName;

    public CustomUserDetails(Long id, String username, String password, List<SimpleGrantedAuthority> roles,String userName)
    {
       this.id=id;
       this.email=username;
       this.password=password;
       this.roles=roles;
       this.userName = userName;
    }
   public String getName()
   {
      return userName;
   }
    public Long getUserId()
    {
        return id;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return roles;
    }

    @Override
    public @Nullable String getPassword()
    {
        return password;
    }

    @Override
    public String getUsername()
    {
        return email;
    }
}
