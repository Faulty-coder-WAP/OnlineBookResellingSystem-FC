package OnlineBookResellingSystem.OBRS_BackEnd.exception.FilterExceptionHandlers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class customAccessDenaiedHandler implements AccessDeniedHandler
{
    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException msg) throws IOException, ServletException
    {
        res.setStatus(HttpServletResponse.SC_FORBIDDEN);
        res.setContentType("application/json");
        res.getWriter().write("""
                {
                "timestamps:"%s",
                "status":403,
                "error":"forbidden",
                "message":"%s",
                "path":"%s"
                }
                """.formatted(LocalDateTime.now(),msg.getMessage(),req.getRequestURI()));
    }
}
