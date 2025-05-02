package kg.attractor.edufood.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PageHolder<T> {
    private List<T> content;
    private Integer page;
    private Integer size;
    private Integer totalPages;
    private Boolean hasNextPage;
    private Boolean hasPreviousPage;

    public PageHolder(List<T> items) {
        content = items;
    }

    public static <T> PageHolder<T> of(List<T> items) {
        return new PageHolder<>(items);
    }
}
