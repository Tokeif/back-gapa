package art.gapa.domain.collage;

import art.gapa.common.web.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;

/**
 * 藏品系列
 *
 * @author JoverZhang
 */
@Getter
@Setter
@Entity
@Where(clause = BaseEntity.WHERE_CLAUSE)
public class CollageSeries extends BaseEntity {

    /**
     * 系列名
     */
    @Column(nullable = false, length = 20)
    private String name;

    /**
     * 作者名
     */
    @Column(nullable = false, length = 20)
    private String autherName;

    /**
     * 系列描述
     */
    @Lob
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

}
