package art.gapa.controller.collage.query;

import art.gapa.common.web.domain.SearchQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author JoverZhang
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class MarketQuery extends SearchQuery {

    @Max(2)
    @Min(0)
    @Schema(title = "排序规则", description = "0: 最新, 1: 价格升序, 2: 价格倒序")
    private int order;

}
