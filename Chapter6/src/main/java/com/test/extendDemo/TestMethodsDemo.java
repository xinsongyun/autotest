package com.test.extendDemo;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestMethodsDemo {

    @Test
    public void testCase1(){
        Assert.assertEquals(1,2);
        //System.out.println("this is testcase1");

    }
    @Test
    public void testCase2(){
        Assert.assertEquals(1,1);
       // System.out.println("this is testcase2");

    }
    @Test
    public void logDemo(){
        Reporter.log("my log");
        throw new RuntimeException("Exception");
    }
}
