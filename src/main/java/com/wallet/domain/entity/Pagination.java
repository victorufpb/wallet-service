package com.wallet.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Pagination<T> {
    private List<T> content;
    private int page;
    private int size;
    private int totalPages;
}
