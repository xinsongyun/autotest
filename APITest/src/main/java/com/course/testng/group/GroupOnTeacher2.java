package com.course.testng.group;

import org.testng.annotations.Test;

@Test(groups="teacher")
public class GroupOnTeacher2 {
    public void testTeacher2(){
        System.out.println("testTeacher2");
    }
}
