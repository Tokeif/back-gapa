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
import java.time.LocalDateTime;

/**
 * 藏品种类
 *
 * @author JoverZhang
 */
@Getter
@Setter
@Entity
@Table(indexes = @Index(name = "idx_series_id", columnList = "series_id"))
@Where(clause = BaseEntity.WHERE_CLAUSE)
public class CollageType extends BaseEntity {

    /**
     * 藏品名
     */
    @Column(nullable = false, length = 20)
    private String name;

    /**
     * 藏品系列
     */
    @ManyToOne
    @JoinColumn(nullable = false, foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private CollageSeries series;

    /**
     * 发行价格
     */
    @Column(nullable = false, precision = 11, scale = 2)
    private BigDecimal releasePrice;

    /**
     * 发行量
     */
    @Column(nullable = false)
    private Integer releaseQuantity;

    /**
     * 流通量
     */
    @Column(nullable = false)
    private Integer circulationQuantity;

    /**
     * 发行时间
     */
    @Column(nullable = false)
    private LocalDateTime releaseTime;

    /**
     * 图片地址
     */
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String picture;

    /**
     * 作品描述
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

}
