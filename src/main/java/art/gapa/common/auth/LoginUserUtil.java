package art.gapa.common.auth;

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
        return jwtUtil.parse(authorizations);
    }

}
