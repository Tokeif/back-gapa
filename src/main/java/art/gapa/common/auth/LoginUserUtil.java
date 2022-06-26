package art.gapa.common.auth;

import art.gapa.common.web.exception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;

/**
 * @author JoverZhang
 */
@Component
@RequiredArgsConstructor
public class LoginUserUtil {

    private final HttpServletRequest request;

    private final JwtUtil jwtUtil;

    public LoginUser loginUser() {
        String authorizations = request.getHeader("Authorization");
        Assert.notNull(authorizations, "Unauthorized");
        try {
            return jwtUtil.parse(authorizations);
        } catch (RuntimeException e) {
            throw new UnauthorizedException("未登录");
        }
    }

}
