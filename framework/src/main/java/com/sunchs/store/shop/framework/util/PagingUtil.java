package com.sunchs.store.shop.framework.util;

import com.baomidou.mybatisplus.plugins.Page;
import com.sunchs.store.shop.framework.bean.PagingList;


import java.util.ArrayList;
import java.util.List;

public class PagingUtil {

    public static int getSkip(int pageNow, int pageSize) {
        return (pageNow - 1) * pageSize;
    }

    public static <T> PagingList<T> getData(List<T> list, long total, int pageNow, int pageSize) {
        if (pageSize == 0) {
            throw new RuntimeException("每页条数不能为零");
        }
        int pages = (int) Math.ceil((double)total / (double)pageSize);
        PagingList<T> page = new PagingList<>();
        page.setTotal(total);
        page.setPages(pages);
        page.setPageNow(pageNow);
        page.setPageSize(pageSize);
        page.setList(list);
        return page;
    }

    /**
     * MyBatis-plus分页 转 自定义分页
     */
    public static <T> PagingList<T> getData(Page<T> pageData) {
        PagingList<T> page = new PagingList<>();
        page.setTotal(pageData.getTotal());
        page.setPages(pageData.getPages());
        page.setPageNow(pageData.getCurrent());
        page.setPageSize(pageData.getSize());
        page.setList(pageData.getRecords());
        return page;
    }

    /**
     * 空分页
     */
    public static <T> PagingList<T> Empty(int pageNow, int pageSize) {
        PagingList<T> page = new PagingList<>();
        page.setTotal(0);
        page.setPages(0);
        page.setPageNow(pageNow);
        page.setPageSize(pageSize);
        page.setList(new ArrayList<>());
        return page;
    }

    /**
     * 空分页
     */
    public static <T> PagingList<T> Empty() {
        PagingList<T> page = new PagingList<>();
        page.setTotal(0);
        page.setPages(0);
        page.setPageNow(1);
        page.setPageSize(10);
        page.setList(new ArrayList<>());
        return page;
    }
}
