package com.seek.client.context.client.domain.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class  PageResponse<T> {
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private List<T> data;

    public PageResponse(Page<T> pageData) {
        this.page = pageData.getNumber();
        this.size = pageData.getSize();
        this.totalElements = pageData.getTotalElements();
        this.totalPages = pageData.getTotalPages();
        this.data = pageData.getContent();
    }
}
