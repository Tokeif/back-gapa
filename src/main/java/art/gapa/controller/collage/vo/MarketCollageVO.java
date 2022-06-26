package art.gapa.controller.collage.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 市场藏品 VO
 *
 * @author JoverZhang
 */
@Data
public class MarketCollageVO {

    @Schema(title = "id")
    private Long id;

    @Schema(title = "系列名")
    private String seriesName;

    @Schema(title = "新品名")
    private String typeName;

    @Schema(title = "图片地址")
    private String picture;

    @Schema(title = "藏品编号")
    private Integer number;

    @Schema(title = "寄售价格")
    private BigDecimal price;

}
