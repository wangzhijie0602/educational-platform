package club._8b1t.controller;

import club._8b1t.pojo.Result;
import club._8b1t.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author 8bit
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
}
