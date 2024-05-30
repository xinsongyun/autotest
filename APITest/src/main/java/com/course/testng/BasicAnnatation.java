package com.course.testng;

import org.testng.annotations.*;

public class BasicAnnatation {
    @BeforeSuite
    public void testSuite(){
        System.out.println("this is suite1");
    }
    @Test
    public void testCase1(){
        System.out.println("this is testcase1");

    }
    @Test
    public void testCase2(){
        System.out.println("this is testcase2");

    }

    @BeforeMethod
    public void testBefore(){
        System.out.println("before");
    }
    @AfterMethod
    public void testAfter(){
        System.out.print("after");
    }
    @BeforeClass
    public void BeforeClass(){
        System.out.println("beforeClass");
    }
    @AfterClass
    public void AfterClass(){
        System.out.println("AfterClass");
    }
    @AfterSuite
    public void afterSuite(){
        System.out.println("this is suite1");
    }
}
