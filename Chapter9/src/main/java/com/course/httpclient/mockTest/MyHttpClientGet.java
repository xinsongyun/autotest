package com.course.httpclient.mockTest;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class MyHttpClientGet extends GetProperites{
    //private CookieStore cookieStore;
    /*@Test
    public void httpGetClientForCookies() throws IOException {
        HttpGet get=new HttpGet(url+getCookiePath);
        DefaultHttpClient client=new DefaultHttpClient();
        HttpResponse response=client.execute(get);
        System.out.println(EntityUtils.toString(response.getEntity()));
        cookieStore=client.getCookieStore();
        List<Cookie> cookies=cookieStore.getCookies();
        for(Cookie cookie:cookies){
            System.out.println(cookie.getName()+":"+cookie.getValue());
        }
    }*/
    @Test
    public void sendRequestWithCookie() throws IOException {
        HttpGet get=new HttpGet(url+sendRequestWithCookie);
        DefaultHttpClient client=new DefaultHttpClient();
        client.setCookieStore(cookieStore);
        HttpResponse response=client.execute(get);
        //System.out.println(response.getStatusLine().getStatusCode());
       System.out.println(EntityUtils.toString(response.getEntity()));

    }

}
