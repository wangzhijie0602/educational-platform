package club._8b1t.service.impl;

import club._8b1t.mapper.UserMapper;
import club._8b1t.model.entity.User;
import club._8b1t.model.enums.Role;
import club._8b1t.service.UserService;
import club._8b1t.utils.PasswordEncoderUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

    private final UserMapper userMapper;

    @Override
    public List<User> getAllUsers(long id) throws Exception {
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new Exception("用户不存在");
        }
        Role role = user.getRole();
//        只有ADMIN才可以查看所有的用户
        if (!Role.ADMIN.equals(role)) {
            throw new Exception("权限不足");
        }

        return userMapper.getAllUsers();
    }

    @Override
    public User getUserInfo(long id) throws Exception {
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new Exception("用户不存在");
        }
        return user;
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
        if (!PasswordEncoderUtils.matches(user.getPassword(), res.getPassword())) {
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
        user.setPassword(PasswordEncoderUtils.encodePassword(user.getPassword()));
        userMapper.insert(user);
        return user.getId();
    }

}
