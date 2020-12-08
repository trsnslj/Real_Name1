package com.realname.marketclient.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.realname.marketclient.bean.User;

/**
 * Created on 2019/6/8 19:43.
 * @description 用sharePreference保存用户
 */
public class UserSharePreference {
    public static boolean SaveUserInfo(Context context, User user)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("user_mes",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putInt("user_id",user.getUserId());
        editor.putString("user_name",user.getUserName());
        editor.commit();
        return  true;
    }

    public static User getUser_mes(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("user_mes",Context.MODE_PRIVATE);
        String username=sharedPreferences.getString("user_name",null);
        int userid=sharedPreferences.getInt("user_id",1);
        if(username==null) return null;
        User user = new User(username,userid);
        return  user;
    }

    public  static void ClearData(Context context)
    {
        SharedPreferences sharedPreferences=context.getSharedPreferences("user_mes",Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
    }


}

