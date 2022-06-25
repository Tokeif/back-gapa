package art.gapa.domain.wallet.service.impl;

import art.gapa.domain.wallet.Wallet;
import art.gapa.domain.wallet.WalletTransaction;
import art.gapa.domain.wallet.repository.WalletRepository;
import art.gapa.domain.wallet.repository.WalletTransactionRepository;
import art.gapa.domain.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author JoverZhang
 */
@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository repository;

    private final WalletTransactionRepository transactionRepository;

    @Override
    public Wallet findByUserId(long userId) {
        return repository.findByUserId(userId);
    }

    @Override
    public Wallet createWallet(long userId) {
        Wallet wallet = Wallet.create(userId);
        return repository.save(wallet);
    }

    @Override
    public void decreaseUserWalletAmount(long userId, BigDecimal amount, long orderId) {
        Wallet wallet = repository.findByUserId(userId);
        wallet.decreaseAmount(amount);
        WalletTransaction transaction = WalletTransaction.create(wallet.getId(), orderId,
                WalletTransaction.Type.OUTGO, amount, wallet.getAmount());
        repository.save(wallet);
        transactionRepository.save(transaction);
    }

}
