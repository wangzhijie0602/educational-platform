package club._8b1t.service;

import club._8b1t.pojo.User;

import java.util.List;

/**
 * @author 8bit
 */
public interface UserService {

    List<User> getAllUsers();

    boolean addUser(User user);

    User login(User user);

    User getUserByUsername(String username);
}
