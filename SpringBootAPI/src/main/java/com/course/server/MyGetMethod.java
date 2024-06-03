package com.course.server;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@Api(value = "/",description = "这是我全部的get方法")
public class MyGetMethod {

    @RequestMapping(value = "/getCookies",method = RequestMethod.GET)
    @ApiOperation(value = "通过这个方法可以获取到Cookies",httpMethod = "GET")
    public String getCookies(HttpServletResponse response){
        //HttpServerletRequest 装请求信息的类
        //HttpServerletResponse  装响应信息的类
        Cookie cookie = new Cookie("login","bear");
        response.addCookie(cookie);
        return "welcome to get cookie";
    }

    /**
     * 要求客户端携带cookies访问
     * 这是一个需要携带cookies信息才能访问的get请求
     */

    @RequestMapping(value = "/sendRequestWithCookies",method = RequestMethod.GET)
    @ApiOperation(value = "通过这个方法可以获取到Cookies",httpMethod = "GET")
    public String getCookies(HttpServletRequest request){
        Cookie[] cookies=request.getCookies();
        if(cookies==null){
            return "must cary cookie,cookie is null";
        }
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("login")&&cookie.getValue().equals("bear")){
                return "passed,you have cookies";
            }
        }

        return "must carry cookie";
    }
    /*
    * need to carry parameters to send get request,start and end is parameter
    * */
    @RequestMapping(value = "/getRequestWithParams",method = RequestMethod.GET)
    @ApiOperation(value = "需要携带参数才能访问get访问",httpMethod = "GET")
    public Map<String,Integer> getList(@RequestParam int start,@RequestParam int end){
        Map<String,Integer> maps=new HashMap<>();
        maps.put("BMW",1000);
        maps.put("BENZ",2000);
        maps.put("FORD",9000);
        return maps;
    }

}
