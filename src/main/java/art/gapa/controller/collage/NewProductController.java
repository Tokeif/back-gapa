package art.gapa.controller.collage;

import art.gapa.common.auth.LoginUser;
import art.gapa.common.web.R;
import art.gapa.common.web.controller.BaseController;
import art.gapa.common.web.domain.SearchQuery;
import art.gapa.controller.collage.assembler.CollageAssembler;
import art.gapa.controller.collage.vo.NewProductDetailVO;
import art.gapa.controller.collage.vo.NewProductVO;
import art.gapa.domain.blockchain.service.BlockInfoRecordService;
import art.gapa.domain.collage.CollageInstance;
import art.gapa.domain.collage.CollageSeries;
import art.gapa.domain.collage.CollageType;
import art.gapa.domain.collage.repository.CollageTypeRepository;
import art.gapa.domain.collage.service.CollageService;
import art.gapa.domain.order.OrderInfo;
import art.gapa.domain.order.service.OrderService;
import art.gapa.domain.wallet.Wallet;
import art.gapa.domain.wallet.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * 新品 Controller
 *
 * @author JoverZhang
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/new-product")
public class NewProductController extends BaseController {

    private final WalletService walletService;

    private final CollageTypeRepository repository;

    private final CollageAssembler assembler;

    private final CollageService collageService;

    private final BlockInfoRecordService blockInfoRecordService;

    private final OrderService orderService;

    @GetMapping
    @Operation(summary = "搜索", tags = NEW_PRODUCT)
    public R<List<NewProductVO>> pageSearch(@Validated SearchQuery query) {
        return R.ok(repository.findAllByNameStartsWithOrderByIdDesc(query.pageReqeust(), query.getSearch())
                .stream().map(assembler::toNewProductVO).toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "详情", tags = NEW_PRODUCT)
    public R<NewProductDetailVO> findById(@Schema(title = "新品 id") @PathVariable long id) {
        Optional<CollageType> typeOptional = repository.findById(id);
        if (typeOptional.isEmpty()) {
            return R.ok();
        }
        CollageType type = typeOptional.get();
        CollageSeries series = type.getSeries();
        return R.ok(assembler.toNewProductVO(series, type));
    }

    @PostMapping("/buy")
    @Transactional(rollbackFor = Exception.class)
    @Operation(summary = "购买", tags = NEW_PRODUCT)
    public R<Long> buy(@Schema(title = "新品 id") long id) {
        final int quantity = 1;
        LoginUser user = loginUser();

        // TODO(Jover): 优化执行顺序

        CollageType type = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("新品不存在"));
        Assert.isTrue(type.inStock(), "新品已售罄");

        // 校验 钱包的余额
        BigDecimal amount = type.getReleasePrice();
        Wallet wallet = walletService.findByUserId(user.getId());
        wallet.checkForDecreaseAmount(amount);

        // 增加新品流通量
        type.increaseCirculationQuantity(quantity);
        // 创建 藏品实例
        CollageInstance collage = collageService.createInstance(type, user.getId());

        // 创建 区块信息记录
        blockInfoRecordService.createRecord(collage.getId(), collage.getUserId());

        // 创建 订单
        OrderInfo order = orderService.createOrder(user.getId(), collage.getId(),
                OrderInfo.Origin.NEW_PRODUCT, OrderInfo.Type.BOUGHT, quantity, amount);

        // 扣减 钱包余额
        walletService.decreaseUserWalletAmountByOrder(user.getId(), amount, order.getId());

        return R.ok(order.getId());
    }

}
