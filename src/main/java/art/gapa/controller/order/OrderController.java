package art.gapa.controller.order;

import art.gapa.common.auth.LoginUser;
import art.gapa.common.web.R;
import art.gapa.common.web.controller.BaseController;
import art.gapa.common.web.domain.PaginationQuery;
import art.gapa.domain.order.OrderInfo;
import art.gapa.domain.order.repository.OrderRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 订单 Controller
 *
 * @author JoverZhang
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController extends BaseController {

    private final OrderRepository repository;

    @GetMapping
    @Operation(summary = "订单列表", tags = ORDER)
    public R<List<OrderInfo>> findList(PaginationQuery query) {
        LoginUser user = loginUser();
        return R.ok(repository.findAllByUserId(query.pageReqeust(), user.getId()));
    }

}
