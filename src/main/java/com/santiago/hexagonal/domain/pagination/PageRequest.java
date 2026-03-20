package com.santiago.hexagonal.domain.pagination;

public class PageRequest {

    private final int page;
    private final int size;

    public PageRequest(int page, int size) {
        this.page = page <= 1 ? 0 : page;
        this.size = size < 0 ? 10 : size > 100 ? 100 : size;
    }

    public int offset() {
        return page * size;
    }

    public int page() {
        return page;
    }

    public int size() {
        return size;
    }
}
