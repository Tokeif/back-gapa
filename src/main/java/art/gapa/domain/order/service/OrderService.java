package art.gapa.domain.order.service;

import art.gapa.domain.order.OrderInfo;

import java.math.BigDecimal;

/**
 * @author JoverZhang
 */
public interface OrderService {

    OrderInfo createOrder(long userId, long collageId, OrderInfo.Origin origin, OrderInfo.Type type,
                          int quantity, BigDecimal amount);

}
