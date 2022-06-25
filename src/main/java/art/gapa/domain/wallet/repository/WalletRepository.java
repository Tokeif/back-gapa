package art.gapa.domain.wallet.repository;

import art.gapa.common.web.repository.BaseRepository;
import art.gapa.domain.wallet.Wallet;
import org.springframework.stereotype.Repository;

/**
 * @author JoverZhang
 */
@Repository
public interface WalletRepository extends BaseRepository<Wallet, Long> {

}
