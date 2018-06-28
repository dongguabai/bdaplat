package com.zj.bda.common.bean;

/**分页入参
 * Created by Dongguabai on 2018-06-14.
 */
public class PageBean {

    // 当前页页码
    private int currentPage;
    // 每页显示条数
    private int rows;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }


}
