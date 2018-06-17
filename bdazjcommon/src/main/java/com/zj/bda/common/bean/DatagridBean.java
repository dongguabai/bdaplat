package com.zj.bda.common.bean;

/**
 * Created by Dongguabai on 2018-06-13.
 */
import java.io.Serializable;
import java.util.List;

public class DatagridBean<T> implements Serializable{

    private Integer total;

    private List<T> rows;

    public DatagridBean() {}

    public DatagridBean(Integer total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
