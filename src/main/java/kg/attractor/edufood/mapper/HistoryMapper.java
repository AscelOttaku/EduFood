package kg.attractor.edufood.mapper;

import kg.attractor.edufood.dto.HistoryDto;
import kg.attractor.edufood.model.History;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HistoryMapper {

    private final OrderMapper orderMapper;

    public HistoryDto mapToDto(History history) {

        return HistoryDto.builder()
                .id(history.getId())
                .orderId(orderMapper.mapToDto(history.getOrder()))
                .amount(history.getAmount())
                .build();
    }

//    public History mapToEntity(HistoryDto dto) {
//
//        History history = new History();
//        history.setId(dto.getId());
//        history.setOrder(orderMapper.mapToEntity(dto.getOrderId()));
//        history.setAmount(dto.getAmount());
//
//        return history;
//    }
}
