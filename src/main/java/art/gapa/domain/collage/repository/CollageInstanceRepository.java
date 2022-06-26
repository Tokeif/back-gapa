package art.gapa.domain.collage.repository;

import art.gapa.common.web.repository.BaseRepository;
import art.gapa.domain.collage.CollageInstance;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author JoverZhang
 */
@Repository
public interface CollageInstanceRepository extends BaseRepository<CollageInstance, Long> {

    List<CollageInstance> findAllByUserId(PageRequest pageRequest, long userId);

    Optional<CollageInstance> findByIdAndUserId(long id, long userId);

}
