package art.gapa.domain.collage.repository;

import art.gapa.common.web.repository.BaseRepository;
import art.gapa.domain.collage.CollageCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author JoverZhang
 */
@Repository
public interface CollageCategoryRepository extends BaseRepository<CollageCategory, Long> {

    List<CollageCategory> findAll(Pageable pageable);

}
