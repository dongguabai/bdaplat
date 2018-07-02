package com.zj.bda.common.bean;

/**
 * Created by Dongguabai on 2018-06-13.
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
