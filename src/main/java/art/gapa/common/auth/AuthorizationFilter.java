package art.gapa.common.auth;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author JoverZhang
 */
@Component
@RequiredArgsConstructor
public class AuthorizationFilter extends OncePerRequestFilter {

    private final List<String> excludeUris = Lists.newArrayList(
            "/user/login",
            // swagger
            "/swagger/*",
            "/swagger-ui/*",
            "/v3/api-docs/*",
            // 新品
            "/new-product/*"
    );

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getRequestURI();
        for (String uri : excludeUris) {
            if (uri.endsWith("/*")) {
                String prefix = uri.substring(0, uri.length() - 2);
                if (path.startsWith(prefix)) {
                    return true;
                }
            } else {
                if (path.equals(uri)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization == null) {
            response.setStatus(401);
            return;
        }
        filterChain.doFilter(request, response);
    }

}
