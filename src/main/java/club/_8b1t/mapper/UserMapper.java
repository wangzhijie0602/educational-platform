package club._8b1t.mapper;

import club._8b1t.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * @author 8bit
 */
@Mapper
public interface UserMapper {

    List<User> getAllUsers();

    boolean addUser(User user);

    User getByUsernameAndPassword(User user);

}
