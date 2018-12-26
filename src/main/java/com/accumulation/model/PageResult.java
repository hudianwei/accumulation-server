package com.accumulation.model;

import java.util.List;

/**
 * @Description:
 * @author: HU
 * @date: 2018/9/19 14:06
 */
/*用户包装list数据的类*/
public class PageResult {
    private List<?> data;
    private Integer totalCount;

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "PageResult [data=" + data + ",totalCount=" + totalCount + "]";
    }
}
