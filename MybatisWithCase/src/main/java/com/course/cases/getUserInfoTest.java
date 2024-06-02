package com.course.cases;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.course.config.TestConfig;
import com.course.model.AddUserCase;
import com.course.model.GetUserInfoCase;
import com.course.model.User;
import com.course.utils.DataBaseUtil;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class getUserInfoTest {

        @Test(description = "获得用户信息接口")//first excute login true
        public void getUserInfo() throws  InterruptedException, IOException {
            SqlSession sqlSession= DataBaseUtil.getSqlSession();
            GetUserInfoCase getUserInfoCase= sqlSession.selectOne("getUserInfoCase",1);
            User user =getResult(getUserInfoCase);
            Assert.assertEquals(getUserInfoCase.getName(),user.getUserName());



        }

        private User getResult(GetUserInfoCase getUserInfoCase) throws IOException {
            HttpPost post=new HttpPost(TestConfig.getUserInfoUrl);
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("id",getUserInfoCase.getUserId());
            post.setHeader("content-type","application/json");
            StringEntity entity=new StringEntity(jsonObject.toString());//jsonObject 转json 字符串
            post.setEntity(entity);
            TestConfig.defaultHttpClient.setCookieStore(TestConfig.cookieStore);
            String result= EntityUtils.toString(TestConfig.defaultHttpClient.execute(post).getEntity());
            //System.out.println(result);
            User user= JSON.parseObject(result, User.class);
            return user;
        }
    }

