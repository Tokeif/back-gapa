package art.gapa.domain.order.repository;

import art.gapa.common.web.repository.BaseRepository;
import art.gapa.domain.order.OrderInfo;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author JoverZhang
 */
@Repository
public interface OrderRepository extends BaseRepository<OrderInfo, Long> {

    List<OrderInfo> findAllByUserId(PageRequest pageRequest, long userId);

}
