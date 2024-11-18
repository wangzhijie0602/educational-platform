package club._8b1t.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 8bit
 * @version 1.0
 * @since 1.0
 */
@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:8848") // 允许所有来源
                        .allowedMethods("*") // 允许的HTTP方法
                        .allowedHeaders("*") // 允许所有请求头
                        .allowCredentials(true) // 允许发送Cookie
                        .maxAge(3600); // 预检请求的有效期，单位为秒
            }
        };
    }
}
