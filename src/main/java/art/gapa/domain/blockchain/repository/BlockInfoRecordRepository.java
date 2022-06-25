package art.gapa.domain.blockchain.repository;

import art.gapa.common.web.repository.BaseRepository;
import art.gapa.domain.blockchain.BlockInfoRecord;
import org.springframework.stereotype.Repository;

/**
 * @author JoverZhang
 */
@Repository
public interface BlockInfoRecordRepository extends BaseRepository<BlockInfoRecord, Long> {

}
