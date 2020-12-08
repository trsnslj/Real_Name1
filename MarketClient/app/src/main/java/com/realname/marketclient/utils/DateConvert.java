package com.realname.marketclient.utils;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author  2020/3/5 21:47.
 * @description
 */
public class DateConvert {

    public static String changeDate(Date date)
    {
        System.out.println(date);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formatStr =formatter.format(date);
        System.out.println(formatStr);
        return formatStr;
    }

}
