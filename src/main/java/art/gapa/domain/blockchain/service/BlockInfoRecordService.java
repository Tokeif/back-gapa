package art.gapa.domain.blockchain.service;

import art.gapa.domain.blockchain.BlockInfoRecord;

/**
 * @author JoverZhang
 */
public interface BlockInfoRecordService {

    BlockInfoRecord createRecord(long collageId, long userId);

}
