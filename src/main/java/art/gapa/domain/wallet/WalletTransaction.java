package art.gapa.domain.wallet;

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
 * 钱包流水
 *
 * @author JoverZhang
 */
@Getter
@Setter
@Entity
@Table(indexes = @Index(name = "idx_wallet_id", columnList = "walletId"))
@Where(clause = BaseEntity.WHERE_CLAUSE)
public class WalletTransaction extends BaseEntity {

    /**
     * 钱包 id
     */
    @Column(nullable = false)
    private Long walletId;

    /**
     * 订单 id
     */
    @Column(nullable = false)
    private Long orderId;

    /**
     * 类型
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "enum('INCOME', 'OUTGO')")
    private Type type;

    /**
     * 金额
     */
    @Column(nullable = false, precision = 11, scale = 2)
    private BigDecimal amount;

    /**
     * 结余
     */
    @Column(nullable = false, precision = 11, scale = 2)
    private BigDecimal balance;

    public enum Type {
        /**
         * 收入
         */
        INCOME,
        /**
         * 支出
         */
        OUTGO,
    }

}
