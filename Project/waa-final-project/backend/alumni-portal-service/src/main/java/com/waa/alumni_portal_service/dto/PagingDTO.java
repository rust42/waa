package com.waa.alumni_portal_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Data
public class PagingDTO<T> {
    private int totalPages;
    private long totalElements;
    private int currentPage;
    private List<T> content;
}
