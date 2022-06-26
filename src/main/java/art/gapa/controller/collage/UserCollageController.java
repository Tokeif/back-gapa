package art.gapa.controller.collage;

import art.gapa.common.auth.LoginUser;
import art.gapa.common.web.R;
import art.gapa.common.web.controller.BaseController;
import art.gapa.common.web.domain.PaginationQuery;
import art.gapa.controller.collage.assembler.CollageAssembler;
import art.gapa.controller.collage.vo.ConsignCollageVO;
import art.gapa.domain.collage.CollageConsignRecord;
import art.gapa.domain.collage.CollageInstance;
import art.gapa.domain.collage.repository.CollageConsignRecordRepository;
import art.gapa.domain.collage.repository.CollageInstanceRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
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

    private final CollageConsignRecordRepository consignRecordRepository;

    @GetMapping
    @Operation(summary = "查询藏品", tags = USER)
    @Transactional(readOnly = true)
    public R<List<ConsignCollageVO>> findConsignCollages(PaginationQuery query) {
        LoginUser user = loginUser();
        List<CollageInstance> collages = repository.findAllByUserId(query.pageReqeust(), user.getId());
        return R.ok(collages.stream().map(c -> {
            CollageConsignRecord r = consignRecordRepository.findFirstByCollageIdOrderByIdDesc(c.getId());
            return assembler.toConsignCollageVO(c, r);
        }).toList());
    }

    @PostMapping("/consign")
    @Operation(summary = "寄售藏品", tags = USER)
    @Transactional(rollbackFor = Exception.class)
    public R<Void> consignCollage(long collageId, BigDecimal price) {
        LoginUser user = loginUser();
        CollageInstance collage = repository.findByIdAndUserId(collageId, user.getId())
                .orElseThrow(() -> new IllegalArgumentException("藏品不存在"));

        // 藏品是否正在寄售
        CollageConsignRecord consign = consignRecordRepository.findFirstByCollageIdOrderByIdDesc(collageId);
        if (consign != null && consign.isConsigning()) {
            throw new IllegalArgumentException("藏品正在寄售");
        }
        // 修改 藏品状态
        collage.setStatus(CollageInstance.Status.BEING_CONSIGNED);
        collage.setPrice(price);
        repository.save(collage);
        // 创建 寄售记录
        consign = CollageConsignRecord.create(collage.getId(), user.getId(), price);
        consignRecordRepository.save(consign);
        return R.ok();
    }

}
