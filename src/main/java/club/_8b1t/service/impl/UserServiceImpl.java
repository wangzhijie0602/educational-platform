package club._8b1t.service.impl;

import club._8b1t.mapper.UserMapper;
import club._8b1t.pojo.User;
import club._8b1t.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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

    /**
     * 注册用户
     *
     * @param user 用户的信息,包含username,password,email
     * @return 注册是否成功
     * */
    @Override
    public boolean register(@Validated User user) {
        return userMapper.getUserByUsername(user.getUsername()) == null
//                真正注册的方法,前面全部为true才会执行
                && userMapper.insertUser(user);
    }

    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return 如果用户存在,返回用户实体,不存在返回null
     */
    @Override
    public User getUserByUsername(String username) {
        if (username == null || username.isEmpty()) {
            return null;
        }
        return userMapper.getUserByUsername(username);
    }

}
