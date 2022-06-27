package art.gapa.controller.order;

import art.gapa.common.auth.LoginUser;
import art.gapa.common.web.R;
import art.gapa.common.web.controller.BaseController;
import art.gapa.common.web.domain.PaginationQuery;
import art.gapa.controller.order.assembler.OrderAssembler;
import art.gapa.controller.order.vo.UserOrderVO;
import art.gapa.domain.collage.CollageInstance;
import art.gapa.domain.collage.repository.CollageInstanceRepository;
import art.gapa.domain.order.OrderInfo;
import art.gapa.domain.order.repository.OrderRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 订单 Controller
 *
 * @author JoverZhang
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController extends BaseController {

    private final OrderAssembler assembler;

    private final OrderRepository repository;

    private final CollageInstanceRepository collageInstanceRepository;

    @GetMapping
    @Operation(summary = "订单列表", tags = ORDER)
    @Transactional(readOnly = true)
    public R<List<UserOrderVO>> findList(PaginationQuery query) {
        LoginUser user = loginUser();
        List<OrderInfo> orderList = repository.findAllByUserIdOrderByIdDesc(query.pageReqeust(), user.getId());
        List<Long> collageIds = orderList.stream().map(OrderInfo::getCollageId).toList();
        Map<Long, CollageInstance> collageMap = collageInstanceRepository.findMapByIds(collageIds);
        return R.ok(orderList.stream()
                .map(o -> assembler.toUserOrderVO(o, collageMap.get(o.getCollageId())))
                .toList());
    }

}
