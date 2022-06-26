package art.gapa.domain.collage.repository;

import art.gapa.common.web.repository.BaseRepository;
import art.gapa.domain.collage.CollageConsignmentRecord;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author JoverZhang
 */
@Repository
public interface CollageConsignmentRecordRepository extends BaseRepository<CollageConsignmentRecord, Long> {

    CollageConsignmentRecord findByCollageId(long collageId);

}
