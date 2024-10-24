package com.example.demo.utils;

import com.example.demo.model.User;

public class UserUtils {

    public static boolean isAdmin(User user){
       return user.getUserPrivileges().getPrivilegesNumber() >1;
    }

    public static boolean isModerator(User user){
        return user.getUserPrivileges().getPrivilegesNumber() >0;
    }
}
