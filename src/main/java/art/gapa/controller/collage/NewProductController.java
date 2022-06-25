package art.gapa.controller.collage;

import art.gapa.common.web.R;
import art.gapa.controller.collage.assembler.NewProductAssembler;
import art.gapa.controller.collage.query.NewProductQuery;
import art.gapa.controller.collage.vo.NewProductVO;
import art.gapa.domain.collage.repository.CollageTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 新品 Controller
 *
 * @author JoverZhang
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/new-product")
public class NewProductController {

    private final CollageTypeRepository collageTypeRepository;

    private final NewProductAssembler assembler;

    @GetMapping
    public R<List<NewProductVO>> pageSearch(NewProductQuery query) {
        return R.ok(collageTypeRepository.findAllByNameStartsWith(query.pageReqeust(), query.getSearch())
                .stream()
                .map(v -> assembler.toVO(v, v.getSeries().getName(), v.getName(),
                        v.getCirculationQuantity() < v.getReleaseQuantity()))
                .toList());
    }

}
