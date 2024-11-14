package club._8b1t.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

/**
 * @author 8bit
 */
@Getter
@Setter
public class UserInfoRequest extends User {
    @JsonIgnore
    private String password;
}
