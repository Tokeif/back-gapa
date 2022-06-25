package art.gapa.controller.collage;

import art.gapa.common.web.R;
import art.gapa.controller.collage.assembler.NewProductAssembler;
import art.gapa.controller.collage.query.NewProductQuery;
import art.gapa.controller.collage.vo.NewProductDetailVO;
import art.gapa.controller.collage.vo.NewProductVO;
import art.gapa.domain.collage.CollageSeries;
import art.gapa.domain.collage.CollageType;
import art.gapa.domain.collage.repository.CollageTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * 新品 Controller
 *
 * @author JoverZhang
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/new-product")
public class NewProductController {

    private final CollageTypeRepository repository;

    private final NewProductAssembler assembler;

    @GetMapping
    public R<List<NewProductVO>> pageSearch(@Validated NewProductQuery query) {
        return R.ok(repository.findAllByNameStartsWith(query.pageReqeust(), query.getSearch())
                .stream()
                .map(v -> assembler.toVO(v, v.getSeries().getName(), v.getSeries().getAutherName(),
                        v.getName(), v.inStock()))
                .toList());
    }

    @GetMapping("/{id}")
    public R<NewProductDetailVO> findById(@PathVariable long id) {
        Optional<CollageType> typeOptional = repository.findById(id);
        if (typeOptional.isEmpty()) {
            return R.ok();
        }
        CollageType type = typeOptional.get();
        CollageSeries series = type.getSeries();
        return R.ok(assembler.toDetailVO(type, series.getName(), series.getAutherName(), type.getName(),
                type.inStock(), series.getDescription(), type.getDescription()));
    }

}
