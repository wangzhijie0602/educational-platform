package club._8b1t.controller;

import club._8b1t.model.entity.User;
import club._8b1t.model.response.Result;
import club._8b1t.model.response.UserInfoResponse;
import club._8b1t.service.UserService;
import club._8b1t.utils.JwtUtils;
import club._8b1t.utils.ThreadLocalUtils;
import io.github.linpeilie.Converter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 对用户各种操作的api接口实现
 *
 * @author 8bit
 * @version 1.0
 * @since 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final Converter converter;

    @GetMapping("/users")
    public Result getAllUsers() throws Exception {
        String token = ThreadLocalUtils.getToken();
        long id = JwtUtils.extractUserId(token);
        List<User> userList = userService.getAllUsers(id);
        List<UserInfoResponse> userInfoResponseList = converter.convert(userList, UserInfoResponse.class);

        if (userList.isEmpty()) {
            return Result.error("用户权限不足");
        }

        return Result.success(userInfoResponseList);
    }

    @GetMapping("/userinfo")
    public Result userInfo() throws Exception {
        String token = ThreadLocalUtils.getToken();
        long id = JwtUtils.extractUserId(token);
        User user = userService.getUserInfo(id);
        UserInfoResponse userInfoResponse = converter.convert(user, UserInfoResponse.class);
        return Result.success(userInfoResponse);
    }
}
