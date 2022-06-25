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

    @Schema(title = "藏品名")
    private String collageName;

    @Schema(title = "是否有货")
    private Boolean inStock;

    @Schema(title = "藏品售价")
    private BigDecimal releasePrice;

    @Schema(title = "图片地址")
    private String picture;

}
