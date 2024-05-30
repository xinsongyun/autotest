package com.course.testng.multiThread;

import org.testng.annotations.Test;

public class MultiThreadOnXml {
    @Test
    public void testCase1(){
        System.out.println("this is testcase1 :"+Thread.currentThread().getId());

    }
    @Test
    public void testCase2(){
        System.out.println("this is testcase2 :"+Thread.currentThread().getId());

    }
    @Test
    public void testCase3(){
        System.out.println("this is testcase3 :"+Thread.currentThread().getId());

    }
}
