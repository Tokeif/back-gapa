package art.gapa.domain.wallet.service;

import art.gapa.domain.wallet.Wallet;
import art.gapa.domain.wallet.WalletTransaction;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @author JoverZhang
 */
public interface WalletService {

    Wallet findByUserId(long userId);

    Wallet createWallet(long  userId);

    void increaseUserWalletAmountByOrder(long userId, BigDecimal amount, long orderId);

    void decreaseUserWalletAmountByOrder(long userId, BigDecimal amount, long orderId);

    @Transactional(rollbackFor = Exception.class)
    WalletTransaction increaseUserWalletAmount(long userId, BigDecimal amount);

    @Transactional(rollbackFor = Exception.class)
    WalletTransaction decreaseUserWalletAmount(long userId, BigDecimal amount);

}
