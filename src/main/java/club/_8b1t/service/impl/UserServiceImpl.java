package club._8b1t.service.impl;

import club._8b1t.mapper.UserMapper;
import club._8b1t.pojo.User;
import club._8b1t.pojo.UserInfoRequest;
import club._8b1t.service.UserService;
import club._8b1t.utils.UserValidatorUtil;
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

    /**
     * 注册用户
     *
     * @param user 用户的信息,包含username,password,email
     * @return 注册是否成功
     * */
    @Override
    public boolean register(User user) {
        return UserValidatorUtil.validateUserBasicInfo(user)
//                判断用户是否已经注册
                && userMapper.getUserByUsername(user.getUsername()) == null
//                真正注册的方法,前面全部为true才会执行
                && userMapper.insertUser(user);
    }

    /**
     * 用户登录
     *
     * @param user 用户的信息,包含username,password
     * @return 判断登录
     * */
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
