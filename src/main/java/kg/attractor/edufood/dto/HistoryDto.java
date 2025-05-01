package kg.attractor.edufood.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public class HistoryDto {

    private Long id;
    @NotNull
    private Long orderId;
}

