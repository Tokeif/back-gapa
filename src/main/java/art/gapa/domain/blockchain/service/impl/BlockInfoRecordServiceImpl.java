package art.gapa.domain.blockchain.service.impl;

import art.gapa.domain.blockchain.BlockInfoRecord;
import art.gapa.domain.blockchain.repository.BlockInfoRecordRepository;
import art.gapa.domain.blockchain.service.BlockInfoRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author JoverZhang
 */
@Service
@RequiredArgsConstructor
public class BlockInfoRecordServiceImpl implements BlockInfoRecordService {

    private final BlockInfoRecordRepository repository;

    @Override
    public BlockInfoRecord createRecord(long collageId, long userId) {
        BlockInfoRecord blockInfoRecord = BlockInfoRecord.create(collageId, userId);
        repository.save(blockInfoRecord);
        return blockInfoRecord;
    }

}
