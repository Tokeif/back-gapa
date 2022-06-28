package art.gapa.domain.collage.repository;

import art.gapa.common.web.domain.BaseEntity;
import art.gapa.common.web.repository.BaseRepository;
import art.gapa.domain.collage.CollageInstance;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author JoverZhang
 */
@Repository
public interface CollageInstanceRepository extends BaseRepository<CollageInstance, Long> {

    List<CollageInstance> findAllByIdIsIn(List<Long> ids);

    default Map<Long, CollageInstance> findMapByIds(List<Long> ids) {
        return findAllByIdIsIn(ids).stream().collect(Collectors.toMap(BaseEntity::getId, v -> v));
    }

    List<CollageInstance> findAllByUserIdOrderByIdDesc(PageRequest pageRequest, long userId);

    /**
     * TODO: 完善按寄售时间倒叙排序
     */
    @Query("SELECT i FROM CollageInstance i WHERE i.status = :status AND i.type.name LIKE :name% ORDER BY i.updatedAt DESC")
    List<CollageInstance> searchByStatus(PageRequest pageRequest, String name, CollageInstance.Status status);

    Optional<CollageInstance> findByIdAndUserId(long id, long userId);

}
