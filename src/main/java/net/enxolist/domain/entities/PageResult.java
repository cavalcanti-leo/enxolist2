package net.enxolist.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class PageResult<T> {
    private List<T> content;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;

    public <R> PageResult<R> map(Function<? super T, R> mapper) {
        List<R> mappedContent = this.content.stream()
                .map(mapper)
                .toList();

        return new PageResult<>(
                mappedContent,
                this.page,
                this.size,
                this.totalElements,
                this.totalPages
        );
    }

    public static <T> PageResult<T> from(Page<T> page) {
        return new PageResult<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }
}
