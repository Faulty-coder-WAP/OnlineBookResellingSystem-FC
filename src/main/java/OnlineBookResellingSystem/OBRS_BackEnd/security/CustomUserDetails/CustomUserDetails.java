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
    private User details;

    public CustomUserDetails(User details)
    {
        this.details = details;
    }

    public Long getUserId()
    {
        return details.getUser_id();
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
       Set<AllowedRoles> roles=details.getRoles();
       return  roles.stream().map(role-> new SimpleGrantedAuthority("ROLE_"+role.name())).collect(Collectors.toSet());
    }

    @Override
    public @Nullable String getPassword() {
        return details.getPassword();
    }

    @Override
    public String getUsername() {
        return details.getUserName();
    }
}
