package art.gapa.domain.user.repository;

import art.gapa.common.web.repository.BaseRepository;
import art.gapa.domain.user.UserInfo;
import org.springframework.stereotype.Repository;

/**
 * @author JoverZhang
 */
@Repository
public interface UserRepository extends BaseRepository<UserInfo, Long> {

    UserInfo findByPhone(String phone);

}
