package art.gapa.domain.wallet.repository;

import art.gapa.common.web.repository.BaseRepository;
import art.gapa.domain.wallet.WalletTransaction;
import org.springframework.stereotype.Repository;

/**
 * @author JoverZhang
 */
@Repository
public interface WalletTransactionRepository extends BaseRepository<WalletTransaction, Long> {

}
