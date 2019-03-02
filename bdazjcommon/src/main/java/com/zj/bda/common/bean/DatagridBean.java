package com.zj.bda.common.bean;

/**
 * @author Dongguabai
 * @date 2018-07-01 13:30
 */
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class DatagridBean<T> implements Serializable{

    private Integer total;

    private List<T> rows;

}
