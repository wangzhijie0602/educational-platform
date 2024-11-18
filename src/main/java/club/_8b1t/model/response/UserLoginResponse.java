package club._8b1t.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * 登录接口的返回类
 *
 * @author 8bit
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponse {
    private UserInfoResponse userInfo;
    private String accessToken;
    private String refreshToken;
    private Date expires;
}
