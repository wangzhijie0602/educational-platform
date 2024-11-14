package club._8b1t.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author 8bit
 * @version 1.0
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9_]{3,16}$")
    private String username;

    @JsonIgnore
    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
    private String password;

    @NotNull
    @Email
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

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}


