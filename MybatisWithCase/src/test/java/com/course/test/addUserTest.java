package com.course.test;

import com.alibaba.fastjson.JSONObject;
import com.course.config.TestConfig;
import com.course.model.AddUserCase;
import com.course.utils.DataBaseUtil;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class addUserTest {
    @Test(description = "添加用户接口接口")//first excute login true
    public void addUser() throws  InterruptedException, IOException {
        SqlSession sqlSession= DataBaseUtil.getSqlSession();
        AddUserCase addUserCase= sqlSession.selectOne("addUserCase",1);
        String result =getResult(addUserCase);
        Assert.assertEquals(addUserCase.getExpected(),result);



    }

    private String getResult(AddUserCase addUserCase) throws IOException {
        HttpPost post=new HttpPost(TestConfig.addUserUrl);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("userName",addUserCase.getUserName());
        jsonObject.put("password",addUserCase.getPassword());
        jsonObject.put("sex",addUserCase.getSex());
        jsonObject.put("age",addUserCase.getIsDelete());
        jsonObject.put("permission",addUserCase.getPermission());
        jsonObject.put("isDelete",addUserCase.getIsDelete());
        post.setHeader("content-type","application/json");
        StringEntity entity=new StringEntity(jsonObject.toString());//jsonObject 转json 字符串
        post.setEntity(entity);
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.cookieStore);
        String result= EntityUtils.toString(TestConfig.defaultHttpClient.execute(post).getEntity());
        return result;
    }
}
