package club._8b1t.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author 8bit
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String username;
    private String password;
    private String email;
    private String name;
    private Role role;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Short isDeleted;

    public enum Role {
        ADMIN, TEACHER, STUDENT
    }

    public enum Status {
        ACTIVE, INACTIVE, SUSPENDED
    }

}


