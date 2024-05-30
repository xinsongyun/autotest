package com.course.testng.group;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.Test;

public class GroupsOnMethod {
    @Test(groups="server")
    public void serverCase1(){
        System.out.println("this is servercase1");

    }
    @Test(groups="server")
    public void serverCase2(){
        System.out.println("this is servercase2");

    }
    @Test(groups="client")
    public void clientCase1(){
        System.out.println("this is clientcase1");

    }
    @Test(groups="client")
    public void clientCase2(){
        System.out.println("this is clientcase2");

    }
    @BeforeGroups("server")
    public void BeforeGroupsServer(){
        System.out.println("this is beforeGroupServer");

    }
    @AfterGroups("server")
    public void AfterGroupsServer(){
        System.out.println("this is beforeGroupServer");

    }

}
