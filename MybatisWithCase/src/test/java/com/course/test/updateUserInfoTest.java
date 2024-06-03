package com.course.test;

import com.alibaba.fastjson.JSONObject;
import com.course.config.TestConfig;
import com.course.model.UpdateUserInfoCase;
import com.course.model.User;
import com.course.utils.DataBaseUtil;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class updateUserInfoTest {
    public class updateUserInfoCse {
        @Test(description = "更新用户信息接口")//first excute login true
        public void updateUserInfo() throws InterruptedException, IOException {
            SqlSession sqlSession = DataBaseUtil.getSqlSession();
            UpdateUserInfoCase updateUserInfoCase = sqlSession.selectOne("updateUserInfoCase", 1);
            User user = getResult(updateUserInfoCase);
            Assert.assertEquals(updateUserInfoCase.getUserName(), user.getUserName());


        }

        private User getResult(UpdateUserInfoCase updateUserInfoCase) throws IOException {
            HttpPost post = new HttpPost(TestConfig.updateUserInfoUrl);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", updateUserInfoCase.getUserId());
            jsonObject.put("userName", updateUserInfoCase.getUserName());
            //System.out.println(updateUserInfoCase.getUserName());
            //jsonObject.put("password",updateUserInfoCase.getPassword());
            jsonObject.put("sex", updateUserInfoCase.getSex());
            jsonObject.put("age", updateUserInfoCase.getIsDelete());
            jsonObject.put("permission", updateUserInfoCase.getPermission());
            jsonObject.put("isDelete", updateUserInfoCase.getIsDelete());
            post.setHeader("content-type", "application/json");
            StringEntity entity = new StringEntity(jsonObject.toString());//jsonObject 转json 字符串
            post.setEntity(entity);
            TestConfig.defaultHttpClient.setCookieStore(TestConfig.cookieStore);
            String result=EntityUtils.toString(TestConfig.defaultHttpClient.execute(post).getEntity());
            System.out.println(result);
            Assert.assertEquals(Integer.parseInt(result),1);
            User user = DataBaseUtil.getResult(updateUserInfoCase.getUserId());
            return user;
        }
    }
}
