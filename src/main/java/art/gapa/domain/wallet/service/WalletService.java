package art.gapa.domain.wallet.service;

import art.gapa.domain.wallet.Wallet;

import java.math.BigDecimal;

/**
 * @author JoverZhang
 */
public interface WalletService {

    Wallet findByUserId(long userId);

    Wallet createWallet(long  userId);

    void increaseUserWalletAmountByOrder(long userId, BigDecimal amount, long orderId);

    void decreaseUserWalletAmountByOrder(long userId, BigDecimal amount, long orderId);

}
