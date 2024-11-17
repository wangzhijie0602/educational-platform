package club._8b1t.model.request;

import club._8b1t.model.entity.User;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户注册包装类
 *
 * @author 8bit
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@AutoMapper(target = User.class)
public class UserRegisterRequest {

//    用户名长度在3~50之间
//    允许使用字母、数字、下划线和连字符
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_-]{3,50}$", message = "用户名不合法")
    private String username;

//    密码长度在8~255之间
//    密码必须包含至少一个大写字母、一个小写字母、一个数字和一个特殊字符
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,255}$", message = "密码不合法")
    private String password;

    @NotBlank
    @Email(message = "邮箱不合法")
    private String email;

}