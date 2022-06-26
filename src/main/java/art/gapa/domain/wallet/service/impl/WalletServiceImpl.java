package art.gapa.domain.wallet.service.impl;

import art.gapa.domain.wallet.Wallet;
import art.gapa.domain.wallet.WalletTransaction;
import art.gapa.domain.wallet.repository.WalletRepository;
import art.gapa.domain.wallet.repository.WalletTransactionRepository;
import art.gapa.domain.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(rollbackFor = Exception.class)
    public void increaseUserWalletAmountByOrder(long userId, BigDecimal amount, long orderId) {
        Wallet wallet = repository.findByUserId(userId);
        wallet.increaseAmount(amount);
        WalletTransaction transaction = WalletTransaction.create(wallet.getId(), orderId,
                WalletTransaction.Type.INCOME, amount, wallet.getAmount());
        repository.save(wallet);
        transactionRepository.save(transaction);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void decreaseUserWalletAmountByOrder(long userId, BigDecimal amount, long orderId) {
        Wallet wallet = repository.findByUserId(userId);
        wallet.decreaseAmount(amount);
        WalletTransaction transaction = WalletTransaction.create(wallet.getId(), orderId,
                WalletTransaction.Type.OUTGO, amount, wallet.getAmount());
        repository.save(wallet);
        transactionRepository.save(transaction);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WalletTransaction increaseUserWalletAmount(long userId, BigDecimal amount) {
        Wallet wallet = repository.findByUserId(userId);
        wallet.increaseAmount(amount);
        WalletTransaction transaction = WalletTransaction.create(wallet.getId(), 0,
                WalletTransaction.Type.INCOME, amount, wallet.getAmount());
        repository.save(wallet);
        transactionRepository.save(transaction);
        return transaction;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WalletTransaction decreaseUserWalletAmount(long userId, BigDecimal amount) {
        Wallet wallet = repository.findByUserId(userId);
        wallet.decreaseAmount(amount);
        WalletTransaction transaction = WalletTransaction.create(wallet.getId(), 0,
                WalletTransaction.Type.OUTGO, amount, wallet.getAmount());
        repository.save(wallet);
        transactionRepository.save(transaction);
        return transaction;
    }

}
