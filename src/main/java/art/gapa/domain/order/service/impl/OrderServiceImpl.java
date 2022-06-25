package art.gapa.domain.order.service.impl;

import art.gapa.domain.order.OrderInfo;
import art.gapa.domain.order.repository.OrderRepository;
import art.gapa.domain.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author JoverZhang
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Override
    public OrderInfo createOrder(long userId, long collageId, OrderInfo.Origin origin, OrderInfo.Type type,
                                 int quantity, BigDecimal amount) {
        OrderInfo order = OrderInfo.create(userId, collageId, origin, type, quantity, amount);
        order.setState(OrderInfo.State.FINISHED);
        repository.save(order);
        return order;
    }

}
