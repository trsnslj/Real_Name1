package com.realname.marketclient.net;

import android.content.Context;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

    private static final String MY_HOST_URL="http://mysterykiiten.top/MarketServer";
    public static final String IMAGE_PATH="http://mysterykiiten.top/MarketServer/upload/";


    //GET请求需将参数进行封装
    public static  String get(String api,String param) throws IOException {
        String path=MY_HOST_URL+api+param;
        URL url = new URL(path);
        HttpURLConnection connection=(HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(3000);
        int code = connection.getResponseCode();
        String res=null;
        Log.i("code",code+"");
        if(code==200){
            InputStream inputStream = connection.getInputStream();
            InputStreamReader in=new InputStreamReader(inputStream);
            BufferedReader bufferedReader=new BufferedReader(in);
            res = bufferedReader.readLine();
            bufferedReader.close();
        }
        return res;
    }

    //post请求直接传实体类
    public static  String post(String api, Object obj) throws IOException {
        Log.i("httputil","httputil");
        String path=MY_HOST_URL+api;
        URL url = new URL(path);
        HttpURLConnection connection=(HttpURLConnection)url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type","application/json");
        connection.setConnectTimeout(3000);
        Gson gson = new Gson();
        OutputStream outputStream = connection.getOutputStream();
        Log.e("zpx","发送===="+gson.toJson(obj).toString());
        outputStream.write(gson.toJson(obj).getBytes());
        int code = connection.getResponseCode();
        String res=null;
        Log.i("code",code+"");

        if(code==200){
            InputStream inputStream = connection.getInputStream();
            InputStreamReader in=new InputStreamReader(inputStream);
            BufferedReader bufferedReader=new BufferedReader(in);
            res = bufferedReader.readLine();
            bufferedReader.close();
        }else if(code == 400)
        {
            InputStream inputStream = connection.getInputStream();
            InputStreamReader in=new InputStreamReader(inputStream);
            BufferedReader bufferedReader=new BufferedReader(in);
            res = bufferedReader.readLine();
            bufferedReader.close();
        }
        return res;
    }

    //图片工具
    public static void setImageResource(Context context, ImageView view, String imageName){
        Glide.with(context.getApplicationContext())
                .load(IMAGE_PATH+imageName)
                .into(view);
    }


}
