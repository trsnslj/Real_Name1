package com.realname.marketclient.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.realname.marketclient.bean.User;

/**
 * 运用SQLite技术保存注册用户
 */
public class User_SQLite extends SQLiteOpenHelper {

    public User_SQLite( Context context) {
        super(context, "user_db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table user(user_id integer primary key autoincrement," +
                "user_name varchar(20),user_password varchar(100),user_sex varchar(100),user_tel varchar(100),user_img varchar(100))";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //增
    public void adddata(SQLiteDatabase sqLiteDatabase, User user)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put("user_name",user.getUserName());
        contentValues.put("user_password",user.getUserPassword());
        contentValues.put("user_sex",user.getUserSex());
        contentValues.put("user_tel",user.getUserTel());
        contentValues.put("user_img",user.getUserImg());
        sqLiteDatabase.insert("user",null,contentValues);
        sqLiteDatabase.close();
    }
}
