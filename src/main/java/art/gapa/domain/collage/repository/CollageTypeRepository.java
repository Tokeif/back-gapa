package art.gapa.domain.collage.repository;

import art.gapa.common.web.repository.BaseRepository;
import art.gapa.domain.collage.CollageType;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author JoverZhang
 */
@Repository
public interface CollageTypeRepository extends BaseRepository<CollageType, Long> {

    List<CollageType> findAll(Pageable pageable);

}
