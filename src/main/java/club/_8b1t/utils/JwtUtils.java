package club._8b1t.utils;

import club._8b1t.pojo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * 生成用户token的工具类
 *
 * @author 8bit
 * @version 1.1
 * @since 1.0
 */
public class JwtUtils {

    // 使用Keys.secretKeyFor生成一个安全的密钥
    private static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // 生成JWT令牌
    public static String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());
        claims.put("email", user.getEmail());
        claims.put("name", user.getName());
        claims.put("role", user.getRole().name());
        claims.put("status", user.getStatus().name());
        return createToken(claims);
    }

    // 创建JWT令牌
    private static String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    // 验证JWT令牌
    public static Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    // 提取用户名
    public static String extractUsername(String token) {
        return (String) extractAllClaims(token).get("username");
    }

    // 提取用户角色
    public static String extractRole(String token) {
        return (String) extractAllClaims(token).get("role");
    }

    // 提取过期时间
    public static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // 提取声明
    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // 提取所有声明
    private static Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 检查令牌是否过期
    private static Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // 提取User对象
    public static User extractUser(String token) {
        Claims claims = extractAllClaims(token);
        User user = new User();
        user.setId(((Number) claims.get("id")).longValue());
        user.setUsername((String) claims.get("username"));
        user.setEmail((String) claims.get("email"));
        user.setName((String) claims.get("name"));
        user.setRole(User.Role.valueOf((String) claims.get("role")));
        user.setStatus(User.Status.valueOf((String) claims.get("status")));
        return user;
    }
}
