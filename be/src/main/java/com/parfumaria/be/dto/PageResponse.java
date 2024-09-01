package com.parfumaria.be.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PageResponse<T> {
    private long totalItem;
    private List<T> items;

    public static <T> PageResponse<T> success(List<T> items, long totalItem) {
        return PageResponse.<T>builder()
        .totalItem(totalItem)
        .items(items)
        .build();    
    }
}
