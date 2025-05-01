package kg.attractor.edufood.mapper;

import kg.attractor.edufood.dto.DishDto;
import kg.attractor.edufood.dto.HistoryDto;
import kg.attractor.edufood.model.Dish;
import kg.attractor.edufood.model.History;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HistoryMapper {

    HistoryDto mapToDto(History history);

    History mapToEntity(HistoryDto historyDto);

}
