package art.gapa.common.web.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author JoverZhang
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SearchQuery extends PaginationQuery {

    @Schema(title = "搜索")
    private String search = "";

}
