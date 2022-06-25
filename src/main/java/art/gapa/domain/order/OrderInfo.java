package art.gapa.domain.order;

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
 * 订单
 *
 * @author JoverZhang
 */
@Getter
@Setter
@Entity
@Table(indexes = @Index(name = "idx_user_id", columnList = "userId"))
@Where(clause = BaseEntity.WHERE_CLAUSE)
public class OrderInfo extends BaseEntity {

    /**
     * 用户 id
     */
    @Column(nullable = false)
    private Long userId;

    /**
     * 藏品 id
     */
    @Column(nullable = false)
    private Long collageId;

    /**
     * 途径
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "enum('NEW_PRODUCT', 'MARKET')")
    private Origin origin;

    /**
     * 交易类型
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "enum('BOUGHT', 'SOLD')")
    private Type type;

    /**
     * 数量
     */
    @Column(nullable = false)
    private Integer quantity;

    /**
     * 金额
     */
    @Column(nullable = false, precision = 11, scale = 2)
    private BigDecimal amount;

    /**
     * 状态
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "enum('PENDING', 'FINISHED')")
    private State state;

    public static OrderInfo create(long userId, long collageId, Origin origin, Type type,
                                   int quantity, BigDecimal amount) {
        OrderInfo order = new OrderInfo();
        order.setUserId(userId);
        order.setCollageId(collageId);
        order.setOrigin(origin);
        order.setType(type);
        order.setQuantity(quantity);
        order.setAmount(amount);
        order.setState(State.PENDING);
        return order;
    }

    public enum Origin {
        /**
         * 新品
         */
        NEW_PRODUCT,
        /**
         * 市场
         */
        MARKET,
    }

    public enum Type {
        /**
         * 购入
         */
        BOUGHT,
        /**
         * 售出
         */
        SOLD,
    }

    public enum State {
        /**
         * 待处理
         */
        PENDING,
        /**
         * 已完成
         */
        FINISHED,
    }

}
