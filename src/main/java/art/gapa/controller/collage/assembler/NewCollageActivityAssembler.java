package art.gapa.controller.collage.assembler;

import art.gapa.controller.collage.vo.NewCollageActivityVO;
import art.gapa.domain.collage.CollageType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * @author JoverZhang
 */

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface NewCollageActivityAssembler {

    List<NewCollageActivityVO> toVOList(List<CollageType> list);

}
