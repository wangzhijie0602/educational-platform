package club._8b1t.model;

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
    private String firstName;
    private String lastName;
    private String role;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
