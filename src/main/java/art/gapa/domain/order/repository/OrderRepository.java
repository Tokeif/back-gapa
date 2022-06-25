package art.gapa.domain.order.repository;

import art.gapa.common.web.repository.BaseRepository;
import art.gapa.domain.order.OrderInfo;
import org.springframework.stereotype.Repository;

/**
 * @author JoverZhang
 */
@Repository
public interface OrderRepository extends BaseRepository<OrderInfo, Long> {

}
