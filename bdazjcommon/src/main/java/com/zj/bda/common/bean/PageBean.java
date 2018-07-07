package com.zj.bda.common.bean;

import lombok.Builder;
import lombok.Data;

/**分页入参
 * @author Dongguabai
 * @date 2018-07-08 1:42
 */
@Data
@Builder
public class PageBean {

    /**
     * 当前页页码
      */
    private int currentPage;
    /**
     *     每页显示条数
     */
    private int rows;
}
