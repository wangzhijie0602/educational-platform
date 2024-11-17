package club._8b1t.interceptor;

import club._8b1t.utils.JwtUtils;
import club._8b1t.utils.ThreadLocalUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author 8bit
 * @version 1.0
 * @since 1.0
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String token = request.getHeader("Authorization");

        try {
            JwtUtils.extractUsername(token);
            ThreadLocalUtils.setToken(token);
            return true;
        }catch (Exception e){
            response.setStatus(401);
            response.setHeader("Location", "/login");
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ThreadLocalUtils.clearToken();
    }
}
