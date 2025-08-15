package com.wallet.domain.entity;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PaginationFilter {
    private int page;
    private int size;
    private LocalDate startDate;
    private LocalDate endDate;

    private final Long START_DATE_DAYS = 5L;
    private final Long END_DATE_DAYS = 5L;

    public PaginationFilter(int page, int size, LocalDate startDate, LocalDate endDate) {
        this.page = page;
        this.size = size;
        this.startDate = startDate != null ? startDate : LocalDate.now().minusDays(START_DATE_DAYS);
        this.endDate = endDate != null ? endDate : LocalDate.now().plusDays(END_DATE_DAYS);
    }
}
