package club._8b1t.service;

import club._8b1t.pojo.User;
import club._8b1t.pojo.UserInfoRequest;

import java.util.List;

/**
 * @author 8bit
 * @version 1.0
 * @since 1.0
 */
public interface UserService {

    List<User> getAllUsers();

    boolean addUser(User user);

    User login(User user);

    UserInfoRequest getUserByUsername(String username);
}
