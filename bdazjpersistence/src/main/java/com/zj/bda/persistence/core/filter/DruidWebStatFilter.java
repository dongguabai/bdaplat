package com.zj.bda.persistence.core.filter;

import com.alibaba.druid.support.http.WebStatFilter;

/**
 * Created by Dongguabai on 2018-06-19 0:12
 */
//@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*", initParams = { @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*") })
public class DruidWebStatFilter extends WebStatFilter {

}
