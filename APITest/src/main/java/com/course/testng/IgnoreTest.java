package com.course.testng;

import org.testng.annotations.Test;

public class IgnoreTest {

    @Test
    public void testCase1(){
        System.out.println("this is testcase1");

    }
    @Test(enabled = false)
    public void testCase2(){
        System.out.println("this is testcase2");

    }
}
