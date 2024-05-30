package com.course.testng.suite;

import org.testng.annotations.Test;

public class PayTest extends SuiteConfig {
    @Test
    public void PayTest(){

        System.out.println("Pay success: "+Thread.currentThread().getId());
    }

}
