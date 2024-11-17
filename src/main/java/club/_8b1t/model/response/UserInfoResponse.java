package club._8b1t.model.response;

import club._8b1t.model.enums.Role;
import club._8b1t.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * @author 8bit
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResponse {

    private Long id;
    private String username;
    private String email;
    private String name;
    private Role role;
    private Status status;
    private Date createdAt;

}
