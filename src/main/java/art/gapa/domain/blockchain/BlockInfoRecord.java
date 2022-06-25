package art.gapa.domain.blockchain;

import art.gapa.common.web.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Index;
import javax.persistence.Table;

/**
 * 区块信息记录
 *
 * @author JoverZhang
 */
@Getter
@Setter
@Entity
@Table(indexes = @Index(name = "idx_collage_id", columnList = "collageId"))
@Where(clause = BaseEntity.WHERE_CLAUSE)
public class BlockInfoRecord extends BaseEntity {

    /**
     * 拥有者 id
     */
    @Column(nullable = false)
    private Long userId;

    /**
     * 藏品 id
     */
    @Column(nullable = false)
    private Long collageId;

    /**
     * 状态
     */
    @Column(nullable = false, columnDefinition = "enum('PENDING', 'FINISHED')")
    @Enumerated(EnumType.STRING)
    private State state;

    /**
     * 合约地址
     */
    @Column(nullable = false)
    private String contractAddress;

    /**
     * 链上标识
     */
    @Column(nullable = false)
    private String tokenId;

    public enum State {
        /**
         * 待处理
         */
        PENDING,
        /**
         * 已上链
         */
        FINISHED,
    }

}
