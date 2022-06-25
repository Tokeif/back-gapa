package art.gapa.domain.user.service.impl;

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
    private final UserRepository repository;

    @Override
    public UserInfo register(String username, String phone) {
        UserInfo user = UserInfo.create(username, phone);
        repository.save(user);
        return user;
    }

}
