package com.course.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.course.config.TestConfig;
import com.course.model.GetUserInfoCase;
import com.course.model.User;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import java.io.IOException;
import java.io.Reader;

public class DataBaseUtil {
    public static SqlSession getSqlSession() throws IOException {
        Reader reader= Resources.getResourceAsReader("databaseConfig.xml");
        SqlSessionFactory sessionFactory=new SqlSessionFactoryBuilder().build(reader);
        return sessionFactory.openSession();
        }

    public static User getResult(int id) throws IOException {
        HttpPost post=new HttpPost(TestConfig.getUserInfoUrl);
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("id",id);
        post.setHeader("content-type","application/json");
        StringEntity entity=new StringEntity(jsonObject.toString());//jsonObject 转json 字符串
        post.setEntity(entity);
        TestConfig.defaultHttpClient.setCookieStore(TestConfig.cookieStore);
        String result= EntityUtils.toString(TestConfig.defaultHttpClient.execute(post).getEntity());
        User user= JSON.parseObject(result, User.class);
        return user;
    }
    }

