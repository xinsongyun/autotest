package com.course.utils;

import com.course.model.InterFaceName;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigFile {
    private static ResourceBundle resourceBundle=ResourceBundle.getBundle("application", Locale.CHINA);
    public  static String getUrl(InterFaceName name){
        String serverAddress=resourceBundle.getString("test.url");
        String path;
        switch (name){
            case loginCase:
                path=resourceBundle.getString("login.path");
                break;
            case addUserInfoCase:
                path=resourceBundle.getString("addUser.path");
                break;
            case getUserListCase:
                path=resourceBundle.getString("getUserList.path");
                break;
            case updateUserInfoCase:
                path=resourceBundle.getString("updateUserInfo.path");
                break;
            case getUserInfoCase:
                path=resourceBundle.getString("getUserInfo.path");
                break;
            default:
                throw new RuntimeException("no url test founded,please retry set url");


        }
        return serverAddress+path;
    }

}
