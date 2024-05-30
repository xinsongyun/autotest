package com.course.testng;

import org.testng.annotations.Test;

public class TimeOutTest {
    @Test(timeOut = 3000)
    public void testCase1() throws InterruptedException {
        Thread.sleep(4000);
        System.out.println("this is testcase1");


    }
}
