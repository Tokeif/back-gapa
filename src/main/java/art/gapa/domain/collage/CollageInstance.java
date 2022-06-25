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

/**
 * 藏品实例 (唯一个体)
 *
 * @author JoverZhang
 */
@Getter
@Setter
@Entity
@Table(indexes = {
        @Index(name = "idx_category_id", columnList = "category_id"),
        @Index(name = "idx_user_id", columnList = "userId"),
})
@Where(clause = BaseEntity.WHERE_CLAUSE)
public class CollageInstance extends BaseEntity {

    /**
     * 藏品种类
     */
    @ManyToOne
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private CollageType type;

    /**
     * 藏品编号
     */
    @Column(nullable = false)
    private Integer number;

    /**
     * 拥有者 id
     */
    @Column(nullable = false)
    private Long userId;

    /**
     * 状态
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "enum('BEING_HELD', 'BEING_CONSIGNED')")
    private Status status;

    public enum Status {
        /**
         * 被持有中
         */
        BEING_HELD,
        /**
         * 被寄售中
         */
        BEING_CONSIGNED,
    }

}
