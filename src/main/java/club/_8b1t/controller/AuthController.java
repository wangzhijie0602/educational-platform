package club._8b1t.controller;

import club._8b1t.pojo.Result;
import club._8b1t.pojo.User;
import club._8b1t.service.UserService;
import club._8b1t.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author 8bit
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result userLogin(@RequestBody User user) {
        log.info("用户登录: {}", user);
        User u = userService.login(user);

//        用户登录成功,生成并下发令牌
        if (u != null) {
            String jwt = JwtUtil.generateToken(u.getUsername());
            return Result.success(jwt);
        }

        return Result.error("用户名或密码错误");
    }

    @PostMapping("/register")
    public Result userRegister(@RequestBody User user) {
        log.info("用户注册: {}", user);
        return userService.addUser(user) ? Result.success() : Result.error("注册失败!");
    }

}
