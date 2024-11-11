package club._8b1t.controller;

import club._8b1t.pojo.Result;
import club._8b1t.pojo.User;
import club._8b1t.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 8bit
 */
@CrossOrigin("http://localhost:5173")
@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/api/users")
    public Result getAllUsers() {
        return Result.success(userService.getAllUsers());
    }

    @PostMapping("/register")
    public Result userRegister(@RequestBody User user) {
        return userService.addUser(user) ? Result.success() : Result.error("注册失败!");
    }

}
