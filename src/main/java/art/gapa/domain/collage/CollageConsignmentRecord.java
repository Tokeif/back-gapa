package art.gapa.domain.collage;

import art.gapa.common.web.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 藏品寄售记录
 *
 * @author JoverZhang
 */
@Getter
@Setter
@Entity
@Table(indexes = {
        @Index(name = "idx_collage_id", columnList = "collageId"),
        @Index(name = "idx_user_id", columnList = "userId"),
})
@Where(clause = BaseEntity.WHERE_CLAUSE)
public class CollageConsignmentRecord extends BaseEntity {

    /**
     * 藏品实例 id
     */
    @Column(nullable = false)
    private Long collageId;

    /**
     * 用户 id
     */
    @Column(nullable = false)
    private Long userId;

    /**
     * 寄售价格
     */
    @Column(nullable = false, precision = 11, scale = 2)
    private BigDecimal price;

    /**
     * 状态
     */
    @Column(nullable = false)
    private Status status;

    public enum Status {
        /**
         * 寄售中
         */
        ON_CONSIGNMENT,
        /**
         * 锁定中
         */
        LOCKING,
        /**
         * 已完成
         */
        FINISHED,
        /**
         * 已取消
         */
        CANCELED,
    }

}
