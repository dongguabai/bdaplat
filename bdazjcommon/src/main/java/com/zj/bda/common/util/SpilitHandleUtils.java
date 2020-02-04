package com.zj.bda.common.util;

/**
 * @author dongguabai
 * @Description
 * @Date 创建于 2019-08-09 16:13
 */
public class SpilitHandleUtils {
    /**
     *  private void handle() {
     *         int pageNo = 1;
     *         int pageSize = 100;
     *         int totalNo = 1;
     *         do {
     *             PageInfo<Obj> page = service.unCalculate(pageNo, pageSize, new Date());
     *             totalNo = pageNo == 1 ? page.getPages() : totalNo;
     *             List<Obj> list = page.getList();
     *             for (Obj o : list) {
     *                 service.calculate(o);
     *             }
     *             pageNo++;
     *         } while (totalNo >= pageNo);
     *     }
     */
}
