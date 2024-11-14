package club._8b1t.controller;

import club._8b1t.utils.ResultUtil;
import club._8b1t.service.UserService;
import club._8b1t.utils.ThreadLocalUtil;
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
    public ResultUtil getAllUsers() {
        return ResultUtil.success(userService.getAllUsers());
    }

    @GetMapping("/user")
    public ResultUtil getUser(String username) {
        return ResultUtil.success(userService.getUserByUsername(username));
    }

    @GetMapping("/userinfo")
    public ResultUtil userInfo() {
        String username = ThreadLocalUtil.getToken();
        return ResultUtil.success(userService.getUserByUsername(username));
    }
}
