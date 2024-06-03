package com.course.test;

import com.alibaba.fastjson.JSONObject;
import com.course.config.TestConfig;
import com.course.model.InterFaceName;
import com.course.model.LoginCase;
import com.course.utils.ConfigFile;
import com.course.utils.DataBaseUtil;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginInTest {
    @BeforeTest(groups = "loginTrue",description = "loingTest")
    public void beforeTest(){
        TestConfig.addUserUrl= ConfigFile.getUrl(InterFaceName.addUserInfoCase);
        TestConfig.loginUrl=ConfigFile.getUrl(InterFaceName.loginCase);
        TestConfig.getUserInfoUrl=ConfigFile.getUrl(InterFaceName.getUserInfoCase);
        TestConfig.getUserListUrl=ConfigFile.getUrl(InterFaceName.getUserListCase);
        TestConfig.updateUserInfoUrl=ConfigFile.getUrl(InterFaceName.updateUserInfoCase);
        TestConfig.defaultHttpClient=new DefaultHttpClient();
    }
    @Test(groups = "loginTrue",description = "用户成功登陆接口")
    public void loginTrue() throws IOException {

        SqlSession session = DataBaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase",1);
        //下边的代码为写完接口的测试代码
        String result = getResult(loginCase);
        //处理结果，就是判断返回结果是否符合预期
        Assert.assertEquals(loginCase.getExpected(),result);


    }
    @Test(groups = "loginFalse",description = "用户登录失败")
    public void loginFalse() throws IOException {

        SqlSession session = DataBaseUtil.getSqlSession();
        LoginCase loginCase = session.selectOne("loginCase",2);
        //下边的代码为写完接口的测试代码
        String result = getResult(loginCase);
        //处理结果，就是判断返回结果是否符合预期
        Assert.assertEquals(loginCase.getExpected(),result);


    }

    public String getResult(LoginCase loginCase) throws IOException {
        HttpPost post=new HttpPost(TestConfig.loginUrl);
        post.setHeader("content-type","application/json");
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("userName",loginCase.getUserName());
        jsonObject.put("password",loginCase.getPassword());
        StringEntity entity=new StringEntity(jsonObject.toString());//jsonObject 转json 字符串
        post.setEntity(entity);

        String result= EntityUtils.toString(TestConfig.defaultHttpClient.execute(post).getEntity());
        TestConfig.cookieStore=TestConfig.defaultHttpClient.getCookieStore();
        return result;

    }
}
