package com.github.lyrric.model;

import com.github.pagehelper.Page;
import lombok.Data;

import java.util.List;

/**
 * Created on 2018/11/9.
 * 分页返回
 * @author wangxiaodong
 */
@Data
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
}
