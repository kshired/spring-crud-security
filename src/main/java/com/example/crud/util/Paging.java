package com.example.crud.util;

import lombok.Getter;


@Getter
public class Paging {
    public int totalPage;
    public int number;
    public boolean first;
    public boolean last;

    public Paging(int totalPage, int number, boolean first, boolean last) {
        this.totalPage = totalPage;
        this.number = number;
        this.first = first;
        this.last = last;
    }
}
