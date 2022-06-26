package art.gapa.domain.collage.repository;

import art.gapa.common.web.repository.BaseRepository;
import art.gapa.domain.collage.CollageConsignRecord;
import org.springframework.stereotype.Repository;

/**
 * @author JoverZhang
 */
@Repository
public interface CollageConsignRecordRepository extends BaseRepository<CollageConsignRecord, Long> {

    CollageConsignRecord findFirstByCollageIdOrderByIdDesc(long collageId);

}
