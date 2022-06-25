package art.gapa.domain.wallet.service;

import art.gapa.domain.wallet.Wallet;

import java.math.BigDecimal;

/**
 * @author JoverZhang
 */
public interface WalletService {

    Wallet findByUserId(long userId);

    Wallet createWallet(long  userId);

    void decreaseUserWalletAmount(long userId, BigDecimal amount, long orderId);

}
