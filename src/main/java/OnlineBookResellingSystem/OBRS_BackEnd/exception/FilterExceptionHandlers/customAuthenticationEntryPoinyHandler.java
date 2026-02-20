package OnlineBookResellingSystem.OBRS_BackEnd.exception.FilterExceptionHandlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Formatter;

@Component
public class customAuthenticationEntryPoinyHandler implements AuthenticationEntryPoint
{
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException msg) throws IOException, ServletException
    {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        String body = String.format(
                "{\"timestamp\":\"%s\",\"status\":401,\"error\":\"Unauthorized\",\"message\":\"%s\",\"path\":\"%s\"}",
                LocalDateTime.now(),
                msg.getMessage(),
                request.getRequestURI());
        response.getWriter().write(body);
    }
}
