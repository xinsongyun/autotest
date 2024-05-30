package com.course.testng.parameter;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderTest {

    @DataProvider(name = "user")
    public Object[][] user() {
        return new Object[][]{
                {"", "", "账号不能为空"},
                {"admin", " ", "密码不能为空"},
                {" ", "a123456", "账号不能为空"},
                {"ad ", "123456", "账号“ad”不存在"},
                {"admin", "12345", "密码错误"},
        };

    }
    @Test(dataProvider ="user" )
    public void testEcafeLogin(String userName,  String passWord, String expectText) {
        System.out.println(userName+":"+passWord+":"+expectText);
    }
}
