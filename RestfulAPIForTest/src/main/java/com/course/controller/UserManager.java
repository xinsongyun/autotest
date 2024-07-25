package com.course.controller;

import com.course.model.User;
import com.course.utils.TokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@Api(value = "v1", description = "用户管理系统")
@RequestMapping("v1")
public class UserManager {


    //首先获取一个执行sql语句的对象

    @Autowired
    private SqlSessionTemplate template;

    @ApiOperation(value = "登陆接口", httpMethod = "POST")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(HttpServletResponse response, @RequestBody User user,HttpServletRequest request) {
        int i = template.selectOne("login", user);
        //Boolean x=verifyCookies(request);
       // System.out.println("x:"+x);
        //if(!x){
           // throw new RuntimeException("no cookies added");
       // }
        Cookie cookie = new Cookie("login", "true");
       response.addCookie(cookie);
        if (i>=1) {
            String token = TokenUtils.generateToken();
            response.setHeader("auth", token);

            return "login success";
        } else {
            throw new RuntimeException("login failed");
        }


    }

    @ApiOperation(value = "添加用户接口", httpMethod = "POST")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public boolean addUser(HttpServletRequest request, @RequestBody User user) {
        //Boolean x = verifyCookies(request);
        Boolean x = verifyToken(request);
        int result = 0;
        if (x) {
            result = template.insert("addUser", user);
        }else{
            throw  new RuntimeException("with out token");
        }

        if (result > 0) {
            //log.info("添加用户的数量是:"+result);
            return true;
        }
        throw new RuntimeException("add failed");
    }

    private Boolean verifyCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            // log.info("cookies为空");
            return false;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("login") &&
                    cookie.getValue().equals("true")) {
                //  log.info("cookies验证通过");
                return true;
            }
        }
        return false;
    }

    private Boolean verifyToken(HttpServletRequest request) {
        String token = request.getHeader("auth");
        if (token == null) {
            // log.info("cookies为空");
            return false;
        } else {
            return true;
        }
    }


    @ApiOperation(value = "获取用户(列表)信息接口", httpMethod = "POST")
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    public User getUserInfo(HttpServletRequest request, @RequestBody User user) {
        Boolean x = verifyCookies(request);
        if (x == true) {
            User result = template.selectOne("getUserInfo", user);
            // log.info("getUserInfo获取到的用户数量是" +users.size());
            return result;
        } else {
            return null;
        }
    }


    @ApiOperation(value = "更新/删除用户接口", httpMethod = "POST")
    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.POST)
    public int updateUser(HttpServletRequest request, @RequestBody User user) {
        Boolean x = verifyCookies(request);
        int i = 0;
        if (x == true) {
            i = template.update("updateUserInfo", user);
        }
        //log.info("更新数据的条目数为:" + i);
        return i;

    }

    @ApiOperation(value = "获得token", httpMethod = "POST")
    @RequestMapping(value = "/getToken", method = RequestMethod.GET)
    public String getTokenUser(HttpServletRequest request) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", "success");
        String token = TokenUtils.generateToken();
        jsonObject.put("token", token);
        return jsonObject.toString();


    }

    @ApiOperation(value = "获得cookie", httpMethod = "GET")
    @RequestMapping(value = "/getCookie", method = RequestMethod.GET)
    public String getCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        //jmeter regex expression 测试使用
        JSONObject jsonObject=new JSONObject();
       // User user=new User();
        //user.setUserName("hahaha");
        jsonObject.put("key","hahaha");
        jsonObject.put("user",jsonObject.toString());
        return jsonObject.toString();


    }


}
