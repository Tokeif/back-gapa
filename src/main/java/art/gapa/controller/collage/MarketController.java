package art.gapa.controller.collage;

import art.gapa.common.auth.LoginUser;
import art.gapa.common.web.R;
import art.gapa.common.web.controller.BaseController;
import art.gapa.controller.collage.assembler.CollageAssembler;
import art.gapa.controller.collage.query.MarketQuery;
import art.gapa.controller.collage.vo.MarketCollageDetailVO;
import art.gapa.controller.collage.vo.MarketCollageVO;
import art.gapa.domain.collage.CollageConsignRecord;
import art.gapa.domain.collage.CollageInstance;
import art.gapa.domain.collage.repository.CollageConsignRecordRepository;
import art.gapa.domain.collage.repository.CollageInstanceRepository;
import art.gapa.domain.order.OrderInfo;
import art.gapa.domain.order.service.OrderService;
import art.gapa.domain.wallet.Wallet;
import art.gapa.domain.wallet.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author JoverZhang
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/market")
public class MarketController extends BaseController {

    private final CollageAssembler assembler;

    private final CollageInstanceRepository repository;

    private final CollageConsignRecordRepository consignRecordRepository;

    private final WalletService walletService;

    private final OrderService orderService;

    @GetMapping
    @Transactional(readOnly = true)
    @Operation(summary = "搜索", tags = MARKET)
    public R<List<MarketCollageVO>> pageSearch(MarketQuery query) {
        List<CollageInstance> collages = repository.searchByStatus(query.pageReqeust(), query.getSearch(),
                CollageInstance.Status.BEING_CONSIGNED);
        return R.ok(collages.stream().map(c -> {
            CollageConsignRecord consignRecord = consignRecordRepository.findFirstByCollageIdOrderByIdDesc(c.getId());
            return assembler.toMarketCollageVO(c, consignRecord);
        }).toList());
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    @Operation(summary = "详情", tags = MARKET)
    public R<MarketCollageDetailVO> findById(@Schema(title = "藏品实例 id") @PathVariable long id) {
        CollageInstance collage = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("藏品不存在"));
        CollageConsignRecord consignRecord = consignRecordRepository.findFirstByCollageIdOrderByIdDesc(collage.getId());
        return R.ok(assembler.toMarketCollageDetailVO(collage, consignRecord));
    }

    @PostMapping("/buy")
    @ApiResponse(description = "订单 id")
    @Transactional(rollbackFor = Exception.class)
    @Operation(summary = "购买", tags = MARKET)
    public R<Long> buy(@Schema(title = "藏品实例 id") long id) {
        LoginUser user = loginUser();
        CollageInstance collage = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("藏品不存在"));
        CollageConsignRecord consignRecord = consignRecordRepository.findFirstByCollageIdOrderByIdDesc(collage.getId());

        // 交易双方
        long buyUserId = user.getId();
        long sellUserId = collage.getUserId();

        // 尝试扣减 钱包余额
        BigDecimal amount = collage.getPrice();
        Wallet buyWallet = walletService.findByUserId(buyUserId);
        buyWallet.decreaseAmount(amount);

        // 修改 藏品实例 的拥有者
        collage.setStatus(CollageInstance.Status.BEING_HELD);
        collage.setPrice(BigDecimal.ZERO);
        collage.setUserId(user.getId());
        repository.save(collage);

        // 修改 寄售记录 为已完成
        consignRecord.setStatus(CollageConsignRecord.Status.FINISHED);
        consignRecordRepository.save(consignRecord);

        // 创建 双方订单
        OrderInfo buyOrder = orderService.createOrder(buyUserId, collage.getId(),
                OrderInfo.Origin.MARKET, OrderInfo.Type.BOUGHT, 1, amount);
        OrderInfo sellOrder = orderService.createOrder(sellUserId, collage.getId(),
                OrderInfo.Origin.MARKET, OrderInfo.Type.SOLD, 1, amount);

        // 修改双方钱包
        walletService.decreaseUserWalletAmountByOrder(buyUserId, amount, buyOrder.getId());
        walletService.increaseUserWalletAmountByOrder(sellUserId, amount, sellOrder.getId());

        log.info("市场交易: {} 从用户 {} -> {}, 订单: {}|{}", collage.getId(),
                sellUserId, buyUserId, sellOrder.getId(), buyOrder.getId());
        return R.ok(buyOrder.getId());
    }

}
