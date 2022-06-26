package art.gapa.controller.collage;

import art.gapa.common.auth.LoginUser;
import art.gapa.common.web.R;
import art.gapa.common.web.controller.BaseController;
import art.gapa.common.web.domain.PaginationQuery;
import art.gapa.controller.collage.assembler.CollageAssembler;
import art.gapa.controller.collage.vo.ConsignmentCollageVO;
import art.gapa.domain.collage.CollageConsignmentRecord;
import art.gapa.domain.collage.CollageInstance;
import art.gapa.domain.collage.repository.CollageConsignmentRecordRepository;
import art.gapa.domain.collage.repository.CollageInstanceRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户藏品
 *
 * @author JoverZhang
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/collage")
public class UserCollageController extends BaseController {

    private final CollageAssembler assembler;

    private final CollageInstanceRepository repository;

    private final CollageConsignmentRecordRepository consignmentRecordRepository;

    @GetMapping
    @Operation(summary = "查询藏品", tags = USER)
    public R<List<ConsignmentCollageVO>> findConsignmentCollages(PaginationQuery query) {
        LoginUser user = loginUser();
        List<CollageInstance> collages = repository.findByUserId(query.pageReqeust(), user.getId());
        return R.ok(collages.stream().map(c -> {
            CollageConsignmentRecord r = consignmentRecordRepository.findByCollageId(c.getId());
            return assembler.toConsignmentCollageVO(c, r);
        }).toList());
    }

}
