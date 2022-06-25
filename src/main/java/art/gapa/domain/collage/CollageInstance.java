package art.gapa.domain.collage;

import art.gapa.common.web.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@ToString(callSuper = true)
@Where(clause = BaseEntity.WHERE_CLAUSE)
@Table(indexes = {
        @Index(name = "idx_type_id", columnList = "type_id"),
        @Index(name = "idx_user_id", columnList = "userId"),
})
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

    public static CollageInstance create(CollageType type, long userId) {
        CollageInstance instance = new CollageInstance();
        instance.setType(type);
        instance.setNumber(type.getCirculationQuantity());
        instance.setUserId(userId);
        instance.setStatus(Status.BEING_HELD);
        return instance;
    }

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
