package club._8b1t.service.impl;

import club._8b1t.mapper.UserMapper;
import club._8b1t.model.entity.User;
import club._8b1t.service.UserService;
import club._8b1t.utils.JwtUtils;
import club._8b1t.utils.PasswordEncoderUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.linpeilie.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户实体的操作类
 *
 * @author 8bit
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private static final String SALT = "educational-platform";
    private final UserMapper userMapper;

    @Override
    public List<User> getAllUsers(String token) throws Exception {
        String role = JwtUtils.extractRole(token);
//        只有ADMIN才可以查看所有的用户
        if (!"ADMIN".equals(role)) {
            throw new Exception("权限不足");
        }

        return userMapper.getAllUsers();
    }

    @Override
    public User getUserInfo(String token) {
        String username = JwtUtils.extractUsername(token);
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().eq("username", username);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public User userLogin(User user) throws Exception {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper
                .eq("username", user.getUsername())
                .or()
                .eq("email", user.getEmail());
        User res = userMapper.selectOne(queryWrapper);

        if (res == null) {
            throw new Exception("用户名或邮箱不存在");
        }
        if (!PasswordEncoderUtils.matches(user.getPassword() + SALT, res.getPassword())) {
            throw new Exception("密码错误");
        }

        return res;
    }

    @Override
    public long userRegister(User user) throws Exception {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.or()
                .eq("username", user.getUsername())
                .eq("email", user.getEmail());

        if (userMapper.selectOne(queryWrapper) != null) {
            throw new Exception("用户名或邮箱已经存在");
        }
        user.setPassword(PasswordEncoderUtils.encodePassword(user.getPassword() + SALT));
        userMapper.insert(user);
        return user.getId();
    }

}
