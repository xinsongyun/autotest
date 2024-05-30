package com.course.testng.suite;


import org.testng.annotations.Test;

public class LoginTest extends SuiteConfig {
    @Test
    public void loginTest(){

        System.out.println("login success");
    }
}
