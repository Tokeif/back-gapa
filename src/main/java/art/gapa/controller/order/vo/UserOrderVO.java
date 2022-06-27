package art.gapa.controller.order.vo;

import art.gapa.domain.order.OrderInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author JoverZhang
 */
@Data
public class UserOrderVO {

    @Schema(title = "订单 id")
    private Long id;

    @Schema(title = "途径")
    private OrderInfo.Origin origin;

    @Schema(title = "交易类型")
    private OrderInfo.Type type;

    @Schema(title = "数量")
    private Integer quantity;

    @Schema(title = "金额")
    private BigDecimal amount;

    @Schema(title = "订单状态")
    private OrderInfo.State state;

    @Schema(title = "藏品 id")
    private Long collageId;

    @Schema(title = "系列名")
    private String seriesName;

    @Schema(title = "新品名")
    private String typeName;

    @Schema(title = "藏品编号")
    private Integer number;

    @Schema(title = "订单创建时间")
    private LocalDateTime datetime;

}
