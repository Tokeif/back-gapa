package art.gapa.domain.user.service.impl;

import art.gapa.common.auth.JwtUtil;
import art.gapa.domain.user.UserInfo;
import art.gapa.domain.user.repository.UserRepository;
import art.gapa.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author JoverZhang
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JwtUtil jwtUtil;

    private final UserRepository repository;

    @Override
    public String login(String phone, String code) {
        UserInfo user = repository.findByPhone(phone);
        // 注册
        if (user == null) {
            user = UserInfo.create(phone, phone);
            repository.save(user);
        }
        Assert.isTrue("000000".equals(code), "验证码不正确");
        return jwtUtil.build(user);
    }

}
