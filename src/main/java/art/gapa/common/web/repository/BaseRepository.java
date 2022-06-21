package art.gapa.common.web.repository;

import art.gapa.common.web.domain.BaseEntity;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;

/**
 * @author JoverZhang
 */
public interface BaseRepository<T extends BaseEntity, I> extends CrudRepository<T, I> {

    @Override
    @Transactional(rollbackFor = Exception.class)
    default void deleteById(@Nonnull I id) {
        Assert.notNull(id, "The given id must not be null!");
        this.delete(this.findById(id).orElseThrow(() -> new EmptyResultDataAccessException(
                String.format("No entity with id %s exists!", id), 1)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    default void delete(@Nonnull T entity) {
        Assert.notNull(entity, "The entity must not be null!");
        entity.setDeleted(true);
        this.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    default void deleteAllById(@Nonnull Iterable<? extends I> ids) {
        ids.forEach(this::deleteById);
    }

    @Override
    default void deleteAll(@Nonnull Iterable<? extends T> entities) {
        throw new UnsupportedOperationException("Unsupported deleteAll(Iterable<? extends T>)");
    }

    @Override
    default void deleteAll() {
        throw new UnsupportedOperationException("Unsupported deleteAll()");
    }

}
