package application.configuration.jwt;

import application.configuration.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static org.springframework.util.StringUtils.hasText;

// 生成，验证，解析JWT token
@Component
@Log
public class JwtProvider {
//    注入CustomUserDetailsService实例，用于验证登录名是否存在

    @Value("$(jwt.secret)")
    private String jwtSecret;
    @Autowired
    private CustomUserDetailsService userDetailsService;
    private static final long TOKEN_VALIDITY = 1800000; //30 min
    public static final String AUTHORIZATION = "Authorization";

    public String generateToken(String login) {
        Date now = new Date();
        return Jwts.builder()
                .setSubject(login)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

//    解析token，验证其签名和过期时间是否正确
    public boolean validateToken(String token) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
        if (claims.getBody().getExpiration().before(new Date())) {
            return false;
        } else {
            return true;
        }
    }

    public String getLoginFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public Date getExpirationDate(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        return claims.getExpiration();
    }

    public String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(AUTHORIZATION);
        if (hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }

}
