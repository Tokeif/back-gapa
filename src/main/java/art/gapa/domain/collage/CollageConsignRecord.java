package art.gapa.domain.collage;

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
public class CollageConsignRecord extends BaseEntity {

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
    @Enumerated(EnumType.STRING)
    private Status status;

    public static CollageConsignRecord create(long collageId, long userId, BigDecimal price) {
        CollageConsignRecord r = new CollageConsignRecord();
        r.setCollageId(collageId);
        r.setUserId(userId);
        r.setPrice(price);
        r.setStatus(Status.ON_CONSIGNMENT);
        return r;
    }

    /**
     * 正在寄售
     */
    public boolean isConsigning() {
        return status == Status.ON_CONSIGNMENT || status == Status.LOCKING;
    }

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
