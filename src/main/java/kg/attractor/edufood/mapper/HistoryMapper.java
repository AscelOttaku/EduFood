package kg.attractor.edufood.mapper;

import kg.attractor.edufood.dto.HistoryDto;
import kg.attractor.edufood.model.History;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HistoryMapper {

    @Mapping(target = "orderId", source = "order.id")
    HistoryDto mapToDto(History history);

    @Mapping(target = "order.id", source = "orderId")
    History mapToEntity(HistoryDto historyDto);

}
