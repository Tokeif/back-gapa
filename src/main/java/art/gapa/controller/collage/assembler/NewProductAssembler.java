package art.gapa.controller.collage.assembler;

import art.gapa.controller.collage.vo.NewProductDetailVO;
import art.gapa.controller.collage.vo.NewProductVO;
import art.gapa.domain.collage.CollageType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author JoverZhang
 */

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface NewProductAssembler {

    NewProductVO toVO(CollageType collageType, String seriesName, String autherName, String typeName, boolean inStock);

    NewProductDetailVO toDetailVO(CollageType collageType, String seriesName, String autherName, String typeName,
                                  boolean inStock, String seriesDescription, String typeDescription);

}
