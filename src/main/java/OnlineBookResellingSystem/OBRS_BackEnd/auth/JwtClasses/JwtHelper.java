package OnlineBookResellingSystem.OBRS_BackEnd.auth.JwtClasses;

import OnlineBookResellingSystem.OBRS_BackEnd.user.enums.AllowedRoles;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class JwtHelper
{
    @Value("${jwt.secret.key}")
      private  String secKey;

    public String generateToken(String username,Long id,Set<String> roles)
    {
        return Jwts
                .builder()
                .signWith(getSecrectKey(),SignatureAlgorithm.HS256)
                .subject(username)
                .claim("userID",id)
                .claim("Roles",roles)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*10))
                .compact();
    }
    private SecretKey getSecrectKey()
    {
        return Keys.hmacShaKeyFor(secKey.getBytes());
    }

    public String getUsername(String token)
    {
        return extractClaimsFromToken(token).getSubject();
    }

    public Claims extractClaimsFromToken(String token)
    {
        return Jwts
                .parser()
                .verifyWith(getSecrectKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public  Date getExpiryDate(String token)
    {
        return extractClaimsFromToken(token).getExpiration();
    }

    public boolean isExpired(String token)
    {
        return getExpiryDate(token).before(new Date());
    }
}
