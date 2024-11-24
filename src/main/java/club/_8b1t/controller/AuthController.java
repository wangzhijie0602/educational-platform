package club._8b1t.controller;

import club._8b1t.model.entity.User;
import club._8b1t.model.request.UserRegisterRequest;
import club._8b1t.model.response.Result;
import club._8b1t.model.request.UserLoginRequest;
import club._8b1t.model.response.UserInfoResponse;
import club._8b1t.model.response.UserLoginResponse;
import club._8b1t.service.UserService;
import club._8b1t.utils.JwtUtils;
import club._8b1t.utils.ThreadLocalUtils;
import io.github.linpeilie.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
    private final Converter converter;

    @PostMapping("/login")
    public Result login(@RequestBody @Validated UserLoginRequest loginRequest) throws Exception {
        User user = converter.convert(loginRequest, User.class);
        User userInfo = userService.userLogin(user);
        UserInfoResponse userInfoResponse = converter.convert(userInfo, UserInfoResponse.class);

//        用户登录成功,生成并下发令牌
        Long userId = userInfoResponse.getId();
        String accessToken = JwtUtils.generateAccessToken(userId);
        UserLoginResponse userLoginResponse = new UserLoginResponse(accessToken);

        return Result.success(userLoginResponse);

    }

    @PostMapping("/register")
    public Result register(@RequestBody @Validated UserRegisterRequest registerRequest) throws Exception {
        User user = converter.convert(registerRequest, User.class);
        Long registerId = userService.userRegister(user);

        return Result.success(registerId);
    }

    @GetMapping("/current")
    public Result userInfo() throws Exception {
        String token = ThreadLocalUtils.getToken();
        long id = JwtUtils.extractUserId(token);
        User user = userService.getUserInfo(id);
        UserInfoResponse userInfoResponse = converter.convert(user, UserInfoResponse.class);
        return Result.success(userInfoResponse);
    }

}
