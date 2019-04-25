package com.zj.bda.web.util.excel.export;

import jxl.write.WriteException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;


@Controller
public class ExcelController {

    @RequestMapping(value = "/excelTest", method = RequestMethod.GET)
    public void exportBankCheckInfo(HttpServletRequest request, HttpServletResponse response) throws IllegalAccessException, IOException, WriteException {
        OutputStream os = response.getOutputStream();// 取得输出流
        response.reset();// 清空输出流
        List<Person> list = new ArrayList<>();
        list.add(new Person("张三","122","北京","12345678900"));
        list.add(new Person("李四","123","上海","12345678900"));
        list.add(new Person("王五","124","武汉","12345678900"));
        list.add(new Person("赵六","125","南京","12345678900"));
        list.add(new Person("田其","126","广州","12345678900"));
        ByteArrayOutputStream outputStream = ExcelUtil.genExcelStream("电话回访明细报表", list, Person.class, null, null, false);
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="
                + new String(("电话回访明细报表-"+ System.currentTimeMillis()+".xls").getBytes(), "iso-8859-1"));
        try (ServletOutputStream out = response.getOutputStream()) {
            out.write(outputStream.toByteArray());
        }
    }


}
