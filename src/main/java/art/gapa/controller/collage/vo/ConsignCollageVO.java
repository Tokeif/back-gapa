package art.gapa.controller.collage.vo;

import art.gapa.domain.collage.CollageConsignRecord;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author JoverZhang
 */
@Data
public class ConsignCollageVO {

    @Schema(title = "藏品实例 id")
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

    @Schema(title = "寄售价格")
    private BigDecimal price;

    @Schema(title = "寄售状态")
    private CollageConsignRecord.Status status;

}
