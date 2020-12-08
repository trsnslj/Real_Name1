package com.realname.marketclient.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.school.marketclient.R;
import com.realname.marketclient.bean.User;
import com.realname.marketclient.net.API;
import com.realname.marketclient.net.HttpUtil;
import com.realname.marketclient.receiver.MyReceiver;
import com.realname.marketclient.utils.UserSharePreference;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity implements  View.OnClickListener {
    private Button login;
    private Button toRegister;
    private EditText editName;
    private EditText editPwd;
    private boolean flag;
    //返回上一步
    private ImageView login_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    public void init(){
        toRegister=(Button)findViewById(R.id.toRegister);
        toRegister.setOnClickListener(this);
        login_back=findViewById(R.id.login_back);
        login_back.setOnClickListener(this);
        login=(Button)findViewById(R.id.login);
        editName=(EditText)findViewById(R.id.edt_name);
        editPwd=(EditText)findViewById(R.id.edt_pwd);
        EditChangedListener editChangedListener = new EditChangedListener();
        editName.addTextChangedListener(editChangedListener);
        editPwd.addTextChangedListener(editChangedListener);
        login.setOnClickListener(this);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toRegister:
                Log.i("click","click");
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.login_back:
                finish();
                    break;
            case R.id.login:{
                if(!flag) break;
                final String userName=editName.getText().toString();
                final String password=editPwd.getText().toString();
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            User user = new User(userName, password, null, null, null);
                            String result = HttpUtil.post(API.LOGIN, user);
                            Gson gson=new Gson();
                             user = gson.fromJson(result, User.class);
//                            Log.i("result",result);
//                            Log.i("User",user.toString());
                            IntentFilter filter = new IntentFilter("com.broadcast2.MY_ACTION");
                            MyReceiver receiver=new MyReceiver();
                            registerReceiver(receiver,filter);
                            Intent intent=new Intent();
                            intent.setAction("com.broadcast2.MY_ACTION");
                            if(result!=null){
                                intent.putExtra("msg","登录成功！");
                                Intent intent1=new Intent(LoginActivity.this,MainActivity.class);
                                 startActivity(intent1);
                                //保存用户到SharePreferences
                                UserSharePreference userSharePreference = new UserSharePreference();
                                userSharePreference.SaveUserInfo(LoginActivity.this,user);
                            }else{
                                intent.putExtra("msg","用户名或密码错误");
                            }
                            sendBroadcast(intent);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }

        }
    }

    class EditChangedListener implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
        @Override
        public void afterTextChanged(Editable s) {
           if(!editName.getText().toString().equals("")&&!editPwd.getText().toString().equals("")){
               login.setBackgroundResource(R.drawable.bg_of_orange_gradient_radius);
               flag=true;
           }else{
               login.setBackgroundResource(R.drawable.corner_gray_shape);
               flag=false;
           }
        }
    }
}
