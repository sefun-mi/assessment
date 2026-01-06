package com.assessment.api_design.common.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomPageResponse<T> {
    private long size;
    private long number;
    private boolean isLast;
    private boolean isFirst;
    private boolean hasNext;
    private long totalPages;
    private long totalElements;
    private Collection<T> content;

    public CustomPageResponse(Page<T> page){
        this.size = page.getSize();
        this.number = page.getNumber();
        this.isLast = page.isLast();
        this.isFirst = page.isFirst();
        this.hasNext = page.hasNext();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.content = page.getContent();
    }
}