package com.course.httpclient.mockTest;

import netscape.javascript.JSObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class MyHttpClientPost extends GetProperites{
    @Test
    public void sendPostRequest() throws IOException {
        HttpPost post=new HttpPost(url+sendPostWithCookie);
        DefaultHttpClient client=new DefaultHttpClient();
        post.setHeader("content-type","application/json");
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("name","George");
        jsonObject.put("age","18");
        StringEntity entity=new StringEntity(jsonObject.toString());//jsonObject 转json 字符串
        post.setEntity(entity);
        client.setCookieStore(cookieStore);
        HttpResponse response=client.execute(post);
        //System.out.println(EntityUtils.toString(response.getEntity()));
        String result=EntityUtils.toString(response.getEntity());
        //json字符串转object
        jsonObject=new JSONObject(result);
        Assert.assertEquals(jsonObject.get("info"),"OK");
        Assert.assertEquals(jsonObject.get("info"),"bad");



    }
}
