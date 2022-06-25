package art.gapa.domain.wallet.service.impl;

import art.gapa.domain.wallet.Wallet;
import art.gapa.domain.wallet.repository.WalletRepository;
import art.gapa.domain.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author JoverZhang
 */
@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository repository;

    @Override
    public Wallet createWallet(long userId) {
        Wallet wallet = Wallet.create(userId);
        return repository.save(wallet);
    }

}
