package OnlineBookResellingSystem.OBRS_BackEnd.auth.Filters;

import OnlineBookResellingSystem.OBRS_BackEnd.auth.JwtClasses.JwtHelper;
import OnlineBookResellingSystem.OBRS_BackEnd.exception.FilterExceptionHandlers.customAuthenticationEntryPoinyHandler;
import OnlineBookResellingSystem.OBRS_BackEnd.security.CustomUserDetails.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter
{
    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private customAuthenticationEntryPoinyHandler authenticationEntryPoinyHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        String header=request.getHeader("Authorization");
        String name=null;
        String token =null;
        if (header==null || !header.startsWith("Bearer "))
        {
filterChain.doFilter(request,response);
return;
        }
            token=header.substring(7);
            try {
                name = jwtHelper.getUsername(token);
                if (name != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    Claims claims = jwtHelper.extractClaimsFromToken(token);
                    List<String> roleinpayload = claims.get("Roles", List.class);
                    List<SimpleGrantedAuthority> roles = roleinpayload
                            .stream()
                            .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                            .toList();
                    if (!jwtHelper.isExpired(token)) {
                        Long id = Long.parseLong(claims.getSubject());
                        CustomUserDetails userData = new CustomUserDetails(id, null, null, roles, claims.get("userName").toString());
                        UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(userData, null, userData.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(upat);
                    }
                }
                filterChain.doFilter(request,response);
            }
            catch (AuthenticationException exception)
            {
                SecurityContextHolder.clearContext();
                authenticationEntryPoinyHandler.commence(request, response,exception);
                return;
            }
            catch (JwtException exception)
            {
                SecurityContextHolder.clearContext();
                authenticationEntryPoinyHandler.commence(request, response, new BadCredentialsException("Invalid Or Expired token"));
                return;
            }
    }
}
