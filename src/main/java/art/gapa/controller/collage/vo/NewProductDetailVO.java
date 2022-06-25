package art.gapa.controller.collage.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author JoverZhang
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NewProductDetailVO extends NewProductVO {

    @Schema(title = "发行量")
    private Integer releaseQuantity;

    @Schema(title = "流通量")
    private Integer circulationQuantity;

    @Schema(title = "系列描述")
    private String seriesDescription;

    @Schema(title = "作品描述")
    private String typeDescription;

}
