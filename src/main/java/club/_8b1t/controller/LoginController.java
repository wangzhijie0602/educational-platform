package club._8b1t.controller;

import club._8b1t.pojo.Result;
import club._8b1t.pojo.User;
import club._8b1t.service.UserService;
import club._8b1t.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 8bit
 */
@RestController
@Slf4j
public class LoginController {

    @Autowired
    private  UserService userService;

    @PostMapping("/login")
    public Result userLogin(@RequestBody User user) {
        log.info("用户登录: {}", user);
        User u = userService.login(user);

//        用户登录成功,生成并下发令牌
        if (u != null) {
            String jwt = JwtUtils.generateToken(u.getUsername());
            return Result.success(jwt);
        }

        return Result.error("用户名或密码错误");
    }

}
