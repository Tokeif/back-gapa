package art.gapa.controller.order.assembler;

import art.gapa.controller.order.vo.UserOrderVO;
import art.gapa.domain.collage.CollageInstance;
import art.gapa.domain.order.OrderInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * @author JoverZhang
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface OrderAssembler {

    @Mapping(target = "id", source = "o.id")
    @Mapping(target = "type", source = "o.type")
    @Mapping(target = "collageId", source = "i.id")
    @Mapping(target = "seriesName", source = "i.type.series.name")
    @Mapping(target = "typeName", source = "i.type.name")
    @Mapping(target = "datetime", source = "o.createdAt")
    UserOrderVO toUserOrderVO(OrderInfo o, CollageInstance i);

}
