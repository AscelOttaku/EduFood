package kg.attractor.edufood.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class HistoryDto {

    private Long id;

    @NotNull
    private Long orderId;

    private int amount;
}

