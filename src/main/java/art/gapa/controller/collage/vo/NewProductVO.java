package art.gapa.controller.collage.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 新品活动 VO
 *
 * @author JoverZhang
 */
@Data
public class NewProductVO {

    @Schema(title = "id")
    private Long id;

    @Schema(title = "系列名")
    private String seriesName;

    @Schema(title = "作者名")
    private String autherName;

    @Schema(title = "新品名")
    private String typeName;

    @Schema(title = "发行价格")
    private BigDecimal releasePrice;

    @Schema(title = "图片地址")
    private String picture;

    @Schema(title = "是否有货")
    private Boolean inStock;

}
