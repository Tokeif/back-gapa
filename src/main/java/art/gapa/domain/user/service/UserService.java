package art.gapa.domain.user.service;

import art.gapa.domain.user.UserInfo;

/**
 * @author JoverZhang
 */
public interface UserService {

    UserInfo register(String username, String phone);

}
