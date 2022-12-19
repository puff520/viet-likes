package com.likes.common.util;

import com.github.pagehelper.PageInfo;

import java.util.List;

public class PageBaseInfo<T>  extends PageInfo<T> {

    //结果集
    private final List<T> data;

    public List<T> getData() {
        return this.getList();
    }


    public PageBaseInfo(List<T> list) {
        super(list);
        this.data = list;
    }
}
