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
public class NewCollageActivityVO {

    @Schema(title = "id")
    private Long id;

    @Schema(title = "藏品名")
    private String name;

    @Schema(title = "藏品售价")
    private BigDecimal price;

    @Schema(title = "藏品数量上限")
    private Integer numberLimit;

}
