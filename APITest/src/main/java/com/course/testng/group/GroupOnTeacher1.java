package com.course.testng.group;

import org.testng.annotations.Test;

@Test(groups="teacher")
public class GroupOnTeacher1 {
    public void testTeacher1(){
        System.out.println("testTeacher1");
    }
}
