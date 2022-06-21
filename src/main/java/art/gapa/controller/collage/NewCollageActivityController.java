package art.gapa.controller.collage;

import art.gapa.common.web.R;
import art.gapa.controller.collage.assembler.NewCollageActivityAssembler;
import art.gapa.controller.collage.query.NewCollageActivityQuery;
import art.gapa.controller.collage.vo.NewCollageActivityVO;
import art.gapa.domain.collage.entity.CollageCategory;
import art.gapa.domain.collage.repository.CollageCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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
@RequestMapping("/new-collage-activity")
public class NewCollageActivityController {

    private final CollageCategoryRepository collageCategoryRepository;

    private final NewCollageActivityAssembler activityAssembler;

    @GetMapping
    public R<List<NewCollageActivityVO>> findActivities(NewCollageActivityQuery query) {
        PageRequest request = PageRequest.of(query.getPage() - 1, query.getPageSize());
        List<CollageCategory> list = collageCategoryRepository.findAll(request);
        return R.ok(activityAssembler.toVOList(list));
    }

}
