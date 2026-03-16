package com.santiago.hexagonal.domain.pagination;

import java.util.List;

public class PaginatedResult<T> {

    private final int count;
    private final List<T> items;
    private final PaginationInfo paginationInfo;

    // Use a public static record for PaginationInfo
    public static record PaginationInfo(
            int currentPage,
            int perPage,
            int itemCount,
            int pageCount,
            boolean hasNext,
            boolean hasPrevious) {
    }

    public PaginatedResult(int count, List<T> items, PaginationInfo paginationInfo) {
        this.count = count;
        this.items = items;
        this.paginationInfo = paginationInfo;
    }

    public int getCount() {
        return count;
    }

    public List<T> getItems() {
        return items;
    }

    public PaginationInfo getPaginationInfo() {
        return paginationInfo;
    }

    public static <T> PaginatedResult<T> of(List<T> items, int currentPage, int perPage, int totalItems) {
        int pageCount = (int) Math.ceil((double) totalItems / perPage);
        boolean hasNext = currentPage < pageCount - 1;
        boolean hasPrevious = currentPage > 0;

        PaginationInfo paginationInfo = new PaginationInfo(currentPage, perPage, totalItems, pageCount, hasNext,
                hasPrevious);
        return new PaginatedResult<>(totalItems, items, paginationInfo);
    }
}