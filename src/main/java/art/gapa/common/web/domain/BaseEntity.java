package art.gapa.common.web.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.time.LocalDateTime;

/**
 * @author JoverZhang
 */
@Getter
@Setter
@ToString
@MappedSuperclass
public class BaseEntity {

    public static final String WHERE_CLAUSE = "deleted = false";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 版本
     */
    @Version
    @Column(nullable = false)
    private Long version;

    /**
     * 创建时间
     */
    @Column(insertable = false, updatable = false, nullable = false,
            columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3)")
    private LocalDateTime createdAt;

    /**
     * 修改时间
     */
    @Column(insertable = false, updatable = false, nullable = false,
            columnDefinition = "DATETIME(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3)")
    private LocalDateTime updatedAt;

    /**
     * 已删除
     */
    @Column(nullable = false)
    private boolean deleted = false;

}
