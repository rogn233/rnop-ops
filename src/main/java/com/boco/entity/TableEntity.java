package com.boco.entity;

public class TableEntity {
    private int page;
    private int limit;
    private int total;
    private Object rows;
    private String sortOrder;

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Object getRows() {
        return rows;
    }

    public void setRows(Object rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "TableEntity{" +
                "page=" + page +
                ", limit=" + limit +
                ", total=" + total +
                ", rows=" + rows +
                ", sortOrder='" + sortOrder + '\'' +
                '}';
    }
}
