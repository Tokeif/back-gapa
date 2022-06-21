package art.gapa.domain.collage.entity;

import art.gapa.common.web.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 藏品种类
 *
 * @author JoverZhang
 */
@Getter
@Setter
@Entity
@Where(clause = BaseEntity.WHERE_CLAUSE)
public class CollageCategory extends BaseEntity {

    /**
     * 藏品名
     */
    @Column(nullable = false)
    private String name;

    /**
     * 藏品数量上限
     */
    @Column(nullable = false)
    private Integer numberLimit;

    /**
     * 初始售价
     */
    @Column(nullable = false)
    private BigDecimal price;

    /**
     * 投放时间
     */
    @Column(nullable = false)
    private LocalDateTime releaseTime;

}
