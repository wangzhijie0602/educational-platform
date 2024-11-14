package club._8b1t.controller;

import club._8b1t.pojo.Result;
import club._8b1t.service.UserService;
import club._8b1t.utils.ThreadLocalUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public Result getAllUsers() {
        return Result.success(userService.getAllUsers());
    }

    @GetMapping("/user")
    public Result getUser(String username) {
        return Result.success(userService.getUserByUsername(username));
    }

    @GetMapping("/userinfo")
    public Result userInfo() {
        String username = ThreadLocalUtils.getToken();
        return Result.success(userService.getUserByUsername(username));
    }
}
