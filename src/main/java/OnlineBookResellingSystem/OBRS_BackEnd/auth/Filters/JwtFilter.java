package OnlineBookResellingSystem.OBRS_BackEnd.auth.Filters;

import OnlineBookResellingSystem.OBRS_BackEnd.auth.JwtClasses.JwtHelper;
import OnlineBookResellingSystem.OBRS_BackEnd.exception.Dto.JwtExceptionDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter
{
    @Autowired
    private JwtHelper jwtHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException
    {
        String header=request.getHeader("Authorization");
        String name=null;
        String token =null;
        if (header!=null&&header.startsWith("Bearer "))
        {
            token=header.substring(7);
            try {
                name=jwtHelper.getUsername(token);
            }
            catch (JwtException ex)
            {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                JwtExceptionDto dto = new JwtExceptionDto(
                        "Token expired or invalid Please Login Again",
                        401,
                        LocalDateTime.now()
                );
                new ObjectMapper().writeValue(response.getOutputStream(),dto);
            }
        }
        if (name!=null&& SecurityContextHolder.getContext().getAuthentication()==null)
        {
            Claims claims= jwtHelper.extractClaimsFromToken(token);
            List<String> roleinpayload=claims.get("Roles",List.class);
            List<SimpleGrantedAuthority> roles=roleinpayload
                   .stream()
                   .map(role->new SimpleGrantedAuthority("ROLE_"+role))
                   .toList();
            if (!jwtHelper.isExpired(token))
            {
            UsernamePasswordAuthenticationToken upat=new UsernamePasswordAuthenticationToken(claims.getSubject(),null,roles);
            SecurityContextHolder.getContext().setAuthentication(upat);
            }
        }
        filterChain.doFilter(request,response);
    }
}
