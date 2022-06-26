package art.gapa.controller.collage;

import art.gapa.common.web.R;
import art.gapa.common.web.controller.BaseController;
import art.gapa.controller.collage.assembler.CollageAssembler;
import art.gapa.controller.collage.query.MarketQuery;
import art.gapa.controller.collage.vo.MarketCollageVO;
import art.gapa.domain.collage.CollageConsignRecord;
import art.gapa.domain.collage.CollageInstance;
import art.gapa.domain.collage.repository.CollageConsignRecordRepository;
import art.gapa.domain.collage.repository.CollageInstanceRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author JoverZhang
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/market")
public class MarketController extends BaseController {

    private final CollageAssembler assembler;

    private final CollageInstanceRepository repository;

    private final CollageConsignRecordRepository consignRecordRepository;

    @GetMapping
    @Transactional(readOnly = true)
    @Operation(summary = "搜索", tags = MARKET)
    public R<List<MarketCollageVO>> pageSearch(MarketQuery query) {
        List<CollageInstance> collages = repository.searchByStatus(query.pageReqeust(), query.getSearch(),
                CollageInstance.Status.BEING_CONSIGNED);
        return R.ok(collages.stream().map(c -> {
            CollageConsignRecord consignRecord = consignRecordRepository.findFirstByCollageIdOrderByIdDesc(c.getId());
            return assembler.toMarketCollageVO(c, consignRecord);
        }).toList());
    }

}
