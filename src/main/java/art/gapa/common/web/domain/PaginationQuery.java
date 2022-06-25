package art.gapa.common.web.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.domain.PageRequest;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author JoverZhang
 */
@Data
public class PaginationQuery {

    @Min(0)
    @NotNull
    @Schema(title = "分页页码")
    private Integer page;

    @Max(200)
    @Min(1)
    @NotNull
    @Schema(title = "分页大小")
    private Integer pageSize;

    public PageRequest pageReqeust() {
        return PageRequest.of(page, pageSize);
    }

}
