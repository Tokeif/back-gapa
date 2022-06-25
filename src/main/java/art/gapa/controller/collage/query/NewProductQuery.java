package art.gapa.controller.collage.query;

import art.gapa.common.web.domain.PaginationQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author JoverZhang
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class NewProductQuery extends PaginationQuery {

    @Schema(title = "搜索", nullable = true)
    private String search;

}
