package art.gapa.common.auth;

import art.gapa.domain.user.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

/**
 * @author JoverZhang
 */
@Component
public class JwtUtil {

    private static final String SIGN = "asgdfoklsagdlsadfhaklsdhfl123as9fdklhzcx7sakjldfsafkjldg";

    public String build(UserInfo user) {
        return Jwts.builder()
                .setSubject("GaPa")
                .claim("id", user.getId())
                .claim("username", user.getUsername())
                .claim("phone", user.getPhone())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(now())
                .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.MINUTES)))
                .signWith(SignatureAlgorithm.HS256, SIGN)
                .compact();
    }

    public LoginUser parse(String loginUserJwt) {
        Jws<Claims> claims = Jwts.parser()
                .setSigningKey(SIGN)
                .parseClaimsJws(loginUserJwt);
        Claims body = claims.getBody();
        return LoginUser.create(
                body.get("id", Long.class),
                body.get("username", String.class),
                body.get("phone", String.class));
    }

    private Date now() {
        return Date.from(Instant.now());
    }

}
