package club._8b1t.service.impl;

import club._8b1t.mapper.UserMapper;
import club._8b1t.pojo.User;
import club._8b1t.pojo.UserInfoRequest;
import club._8b1t.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 8bit
 * @version 1.0
 * @since 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public boolean addUser(User user) {
//        判空,这三项不能为空
        if (user.getUsername() == null ||
                user.getPassword() == null ||
                user.getEmail() == null
        ) {
            return false;
        }
//        用户名或密码过长
        if (user.getUsername().length() > 50 ||
                user.getPassword().length() < 8 ||
                user.getPassword().length() > 255
        ) {
            return false;
        }
        return userMapper.addUser(user);
    }

    @Override
    public User login(User user) {
        return userMapper.getByUsernameAndPassword(user);
    }

    @Override
    public UserInfoRequest getUserByUsername(String username) {
        if (username == null || username.isEmpty()) {
            return null;
        }
        return userMapper.getUserByUsername(username);
    }


}
