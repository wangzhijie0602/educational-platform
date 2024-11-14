package club._8b1t.mapper;

import club._8b1t.pojo.User;
import club._8b1t.pojo.UserInfoRequest;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @author 8bit
 * @version 1.0
 * @since 1.0
 */
@Mapper
public interface UserMapper {

    List<User> getAllUsers();

    boolean insertUser(User user);

    User getByUsernameAndPassword(User user);

    UserInfoRequest getUserByUsername(String username);

}
