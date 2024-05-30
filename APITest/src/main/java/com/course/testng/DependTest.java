package com.course.testng;

import org.testng.annotations.Test;

public class DependTest {
    @Test
    public void testCase1(){
        System.out.println("this is testcase1");
        throw new RuntimeException("Run Failed");

    }
    @Test(dependsOnMethods="testCase1")
    public void testCase2(){
        System.out.println("this is testcase2");

    }

}
