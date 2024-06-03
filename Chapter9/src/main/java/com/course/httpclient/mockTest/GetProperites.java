package com.course.httpclient.mockTest;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class GetProperites {
    protected String url;
    protected String  getCookiePath;
    private ResourceBundle bundle;
    protected String sendRequestWithCookie;
    protected String sendPostWithCookie;
    protected CookieStore cookieStore;

    @BeforeTest
    public void getProperties() throws IOException {
        bundle=ResourceBundle.getBundle("application", Locale.CHINA);
        url=bundle.getString("test.url");
        getCookiePath=bundle.getString("getCookies.path");
        sendRequestWithCookie = bundle.getString("requestWithCookies.path");
        sendPostWithCookie=bundle.getString("requestWithCookiesPost.path");
        HttpGet get=new HttpGet(url+getCookiePath);
        DefaultHttpClient client=new DefaultHttpClient();
        HttpResponse response=client.execute(get);
        System.out.println(EntityUtils.toString(response.getEntity()));
        cookieStore=client.getCookieStore();

    }
}
