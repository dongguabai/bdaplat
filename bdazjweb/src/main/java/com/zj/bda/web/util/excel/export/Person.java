package com.zj.bda.web.util.excel.export;

/**
 * @author 魏民
 * @Description
 * @Date 创建于 2019-04-24 20:35
 */
public class Person {

    @ExcelAnnotation(id = 1,name ="用户名" ,width = 100)
    private String username;

    @ExcelAnnotation(id = 2,name ="密码" ,width = 100)
    private String password;

    @ExcelAnnotation(id = 3,name ="地址" ,width = 100)
    private String address;

    @ExcelAnnotation(id = 4,name ="电话" ,width = 100)
    private String telephone;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Person(String username, String password, String address, String telephone) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.telephone = telephone;
    }

    public Person() {
    }
}
