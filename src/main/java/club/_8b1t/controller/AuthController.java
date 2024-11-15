package club._8b1t.controller;

import club._8b1t.pojo.Result;
import club._8b1t.pojo.User;
import club._8b1t.service.UserService;
import club._8b1t.utils.JwtUtils;
import club._8b1t.utils.PasswordEncoderUtils;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 用户登录和注册的接口实现
 *
 * @author 8bit
 * @version 1.0
 * @since 1.0
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public Result login(@NotNull String username, @NotNull String password) {
        User user = userService.getUserByUsername(username);
        if (user == null || !PasswordEncoderUtils.matches(password, user.getPassword())) {
            return Result.error("用户名或密码错误");
        }

//        用户登录成功,生成并下发令牌
        String jwt = JwtUtils.generateToken(user);
        return Result.success(jwt);

    }

    @PostMapping("/register")
    public Result register(String username, String password, String email) {
        return userService.register(new User(username, PasswordEncoderUtils.encodePassword(password), email)) ? Result.success() : Result.error("注册失败!");
    }

}
