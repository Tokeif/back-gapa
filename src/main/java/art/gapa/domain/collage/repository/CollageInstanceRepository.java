package art.gapa.domain.collage.repository;

import art.gapa.common.web.repository.BaseRepository;
import art.gapa.domain.collage.CollageInstance;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author JoverZhang
 */
@Repository
public interface CollageInstanceRepository extends BaseRepository<CollageInstance, Long> {

    List<CollageInstance> findAllByUserIdOrderByIdDesc(PageRequest pageRequest, long userId);

    @Query("SELECT i FROM CollageInstance i WHERE i.status = :status AND i.type.name LIKE :name% ORDER BY i.id DESC")
    List<CollageInstance> searchByStatus(PageRequest pageRequest, String name, CollageInstance.Status status);

    Optional<CollageInstance> findByIdAndUserId(long id, long userId);

}
