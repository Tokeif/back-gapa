package art.gapa.common.auth;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 登录用户
 *
 * @author JoverZhang
 */
@Getter
@ToString
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginUser {

    private long id;

    private String username;

    private String phone;

    public static LoginUser create(long id, String username, String phone) {
        return new LoginUser(id, username, phone);
    }

}
