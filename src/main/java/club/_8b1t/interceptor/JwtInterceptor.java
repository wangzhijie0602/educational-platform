package club._8b1t.interceptor;

import club._8b1t.utils.JwtUtil;
import club._8b1t.utils.ThreadLocalUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author 8bit
 */
@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String token = request.getHeader("Authorization");

        try {
            ThreadLocalUtil.setToken(JwtUtil.extractUsername(token));
            return true;
        }catch (Exception e){
            response.setStatus(401);
            response.setHeader("Location", "/login");
            return false;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.clearToken();
    }
}
