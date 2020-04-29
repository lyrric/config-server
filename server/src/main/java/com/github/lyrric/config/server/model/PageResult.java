package com.github.lyrric.config.server.model;

import com.github.pagehelper.Page;

import java.util.List;

/**
 * Created on 2018/11/9.
 * 分页返回
 * @author wangxiaodong
 */
public class PageResult {
    /**
     * 数据总数
     */
    private long totalCount;
    /**
     * 页面总数
     */
    private long totalPage;
    /**
     * 分页数据
     */
    private List data;

    public PageResult() {
    }

    public PageResult(Page page) {
        data = page.getResult();
        totalCount = page.getTotal();
        totalPage = page.getPages();
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}
