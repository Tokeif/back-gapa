package art.gapa.domain.wallet;

import art.gapa.common.web.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.util.Assert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * 用户钱包
 *
 * @author JoverZhang
 */
@Getter
@Setter
@Entity
@Table(indexes = @Index(name = "idx_user_id", columnList = "userId"))
@Where(clause = BaseEntity.WHERE_CLAUSE)
public class Wallet extends BaseEntity {

    /**
     * 用户 id
     */
    @Column(nullable = false)
    private Long userId;

    /**
     * 余额
     */
    @Column(nullable = false, precision = 11, scale = 2)
    private BigDecimal amount;

    public static Wallet create(long userId) {
        Wallet wallet = new Wallet();
        wallet.setUserId(userId);
        wallet.setAmount(BigDecimal.ZERO);
        return wallet;
    }

    public void decreaseAmount(BigDecimal amount) {
        Assert.isTrue(this.amount.compareTo(amount) >= 0, "余额不足");
        this.amount = this.amount.subtract(amount);
    }

}
