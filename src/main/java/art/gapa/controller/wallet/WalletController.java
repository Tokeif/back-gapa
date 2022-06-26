package art.gapa.controller.wallet;

import art.gapa.common.auth.LoginUser;
import art.gapa.common.web.R;
import art.gapa.common.web.controller.BaseController;
import art.gapa.domain.wallet.Wallet;
import art.gapa.domain.wallet.WalletTransaction;
import art.gapa.domain.wallet.repository.WalletRepository;
import art.gapa.domain.wallet.service.WalletService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * 用户 Controller
 *
 * @author JoverZhang
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/wallet")
public class WalletController extends BaseController {

    private final WalletService service;

    private final WalletRepository repository;

    @GetMapping("/info")
    @Operation(summary = "钱包信息", tags = WALLET)
    public R<Wallet> info() {
        LoginUser user = loginUser();
        Wallet wallet = repository.findByUserId(user.getId());
        return R.ok(wallet);
    }

    @PostMapping("/top-up")
    @Operation(summary = "充值", tags = WALLET)
    public R<Long> topUp(@RequestParam BigDecimal amount) {
        LoginUser user = loginUser();
        WalletTransaction transaction = service.increaseUserWalletAmount(user.getId(), amount);
        return R.ok(transaction.getId());
    }

    @PostMapping("/withdraw")
    @Operation(summary = "提现", tags = WALLET)
    public R<Long> withdraw(@RequestParam BigDecimal amount) {
        LoginUser user = loginUser();
        WalletTransaction transaction = service.decreaseUserWalletAmount(user.getId(), amount);
        return R.ok(transaction.getId());
    }

}
