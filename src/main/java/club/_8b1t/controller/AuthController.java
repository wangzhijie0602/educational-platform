package club._8b1t.controller;

import club._8b1t.utils.ResultUtil;
import club._8b1t.pojo.User;
import club._8b1t.service.UserService;
import club._8b1t.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用户登录和注册的接口实现
 *
 * @author 8bit
 * @version 1.0
 * @since 1.0
 */
@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResultUtil userLogin(@RequestBody User user) {
        User u = userService.login(user);

//        用户登录成功,生成并下发令牌
        if (u != null) {
            String jwt = JwtUtil.generateToken(u.getUsername());
            return ResultUtil.success(jwt);
        }

        return ResultUtil.error("用户名或密码错误");
    }

    @PostMapping("/register")
    public ResultUtil userRegister(@RequestBody User user) {
        return userService.register(user) ? ResultUtil.success() : ResultUtil.error("注册失败!");
    }

}
