package kg.attractor.edufood.mapper.impl;

import kg.attractor.edufood.dto.PageHolder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PageHolderWrapper {

    public <T> PageHolder<T> wrap(Page<T> content) {
        return PageHolder.<T>builder()
                .content(content.stream()
                        .toList())
                .page(content.getNumber())
                .size(content.getSize())
                .totalPages(content.getTotalPages())
                .hasNextPage(content.hasNext())
                .hasPreviousPage(content.hasPrevious())
                .build();
    }
}
