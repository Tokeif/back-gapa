package art.gapa.controller.collage.assembler;

import art.gapa.controller.collage.vo.MarketCollageDetailVO;
import art.gapa.controller.collage.vo.MarketCollageVO;
import art.gapa.controller.collage.vo.NewProductDetailVO;
import art.gapa.controller.collage.vo.NewProductVO;
import art.gapa.controller.collage.vo.ConsignCollageVO;
import art.gapa.domain.collage.CollageConsignRecord;
import art.gapa.domain.collage.CollageInstance;
import art.gapa.domain.collage.CollageSeries;
import art.gapa.domain.collage.CollageType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * @author JoverZhang
 */

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface CollageAssembler {

    @Mapping(target = "seriesName", source = "t.series.name")
    @Mapping(target = "autherName", source = "t.series.autherName")
    @Mapping(target = "typeName", source = "t.name")
    @Mapping(target = "inStock", expression = "java(t.inStock())")
    NewProductVO toNewProductVO(CollageType t);

    @Mapping(target = "id", source = "t.id")
    @Mapping(target = "seriesName", source = "s.name")
    @Mapping(target = "autherName", source = "s.autherName")
    @Mapping(target = "typeName", source = "t.name")
    @Mapping(target = "seriesDescription", source = "s.description")
    @Mapping(target = "typeDescription", source = "t.description")
    @Mapping(target = "inStock", expression = "java(t.inStock())")
    NewProductDetailVO toNewProductVO(CollageSeries s, CollageType t);

    @Mapping(target = "id", source = "i.id")
    @Mapping(target = "seriesName", source = "i.type.series.name")
    @Mapping(target = "typeName", source = "i.type.name")
    @Mapping(target = "picture", source = "i.type.picture")
    @Mapping(target = "price", source = "i.price")
    MarketCollageVO toMarketCollageVO(CollageInstance i, CollageConsignRecord r);

    @Mapping(target = "id", source = "i.id")
    @Mapping(target = "seriesName", source = "i.type.series.name")
    @Mapping(target = "typeName", source = "i.type.name")
    @Mapping(target = "picture", source = "i.type.picture")
    @Mapping(target = "price", source = "i.price")
    @Mapping(target = "releaseQuantity", source = "i.type.releaseQuantity")
    @Mapping(target = "circulationQuantity", source = "i.type.circulationQuantity")
    @Mapping(target = "seriesDescription", source = "i.type.series.description")
    @Mapping(target = "typeDescription", source = "i.type.description")
    MarketCollageDetailVO toMarketCollageDetailVO(CollageInstance i, CollageConsignRecord r);

    @Mapping(target = "id", source = "i.id")
    @Mapping(target = "status", source = "i.status")
    @Mapping(target = "seriesName", source = "i.type.series.name")
    @Mapping(target = "autherName", source = "i.type.series.autherName")
    @Mapping(target = "typeName", source = "i.type.name")
    @Mapping(target = "releasePrice", source = "i.type.releasePrice")
    @Mapping(target = "picture", source = "i.type.picture")
    @Mapping(target = "price", source = "i.price")
    ConsignCollageVO toConsignCollageVO(CollageInstance i, CollageConsignRecord r);

}
