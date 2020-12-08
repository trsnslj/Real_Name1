package com.realname.marketclient.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.school.marketclient.R;
import com.realname.marketclient.bean.User;
import com.realname.marketclient.net.API;
import com.realname.marketclient.net.HttpUtil;
import com.realname.marketclient.receiver.MyReceiver;

import java.io.IOException;


public class RegisterActivity extends Activity implements View.OnClickListener {
   //顶部菜单
    private ImageView register_back,register_order;
    private EditText editName,editSex,editTel,editCode,editPwd,editConfirm;
    private Button register;
    private TextView getCode;
    private String realCheckCode;
    private boolean canClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //初始化组件
        init();
    }

    private void init() {
        register_back=findViewById(R.id.register_back);
        register_order=findViewById(R.id.register_order);
        register_order.setOnClickListener(this);
        register_back.setOnClickListener(this);
        editName=findViewById(R.id.edt_name);
        editSex=findViewById(R.id.edt_sex);
        editTel=findViewById(R.id.edt_tel);
        editCode=findViewById(R.id.edt_code);
        editPwd=findViewById(R.id.edt_pwd);
        getCode=findViewById(R.id.get_code);
        getCode.setOnClickListener(this);
        editConfirm=findViewById(R.id.edt_confirm);
        register=findViewById(R.id.register);
        EditChangedListener editChangedListener = new EditChangedListener();
        editName.addTextChangedListener(editChangedListener);
        editSex.addTextChangedListener(editChangedListener);
        editTel.addTextChangedListener(editChangedListener);
        editCode.addTextChangedListener(editChangedListener);
        editPwd.addTextChangedListener(editChangedListener);
        editConfirm.addTextChangedListener(editChangedListener);
        register.setOnClickListener(this);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.register_back:
                finish();
                break;
            case R.id.register_order:{
                Intent intent=new Intent(this, OrderActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.get_code:{
                final String tel=editTel.getText().toString();
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            realCheckCode = HttpUtil.get(API.GET_CODE, "?tel="+tel);
                            Log.i("code",realCheckCode+"");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                break;
            }
            case R.id.register:{
                if(!canClick){
                    Log.i("click","click");
                    break;
                }
                String userName=editName.getText().toString();
                String userSex=editSex.getText().toString();
                String userTel=editTel.getText().toString();
                String checkCode=editCode.getText().toString();
                String password=editPwd.getText().toString();
                String confirm=editConfirm.getText().toString();
                boolean flag=true;
                if(!(checkCode!=null&&checkCode.equals(realCheckCode))){
                    Toast.makeText(RegisterActivity.this,"验证码错误！",Toast.LENGTH_SHORT).show();
                    flag=false;
                }
                if(!(password!=null&&password.equals(confirm))){
                    Toast.makeText(RegisterActivity.this,"密码不匹配！",Toast.LENGTH_SHORT).show();
                    flag=false;
                }
                Log.i("flag",flag+"123");
                if(flag){
                    final User user = new User(userName, password, userSex, userTel, "default.png");
                    //运用SQLite保存注册用户
                    User_SQLite user_sqLite=new User_SQLite(RegisterActivity.this);
                    //返回一个 SQLiteDatabase对象
                    SQLiteDatabase sqLiteDatabase=user_sqLite.getWritableDatabase();
                    user_sqLite.adddata(sqLiteDatabase,user);
                        new Thread(){
                            @Override
                            public void run() {
                                try {
                                   String  result = HttpUtil.post(API.INSERT_USER, user);
                                    Log.i("result",result);
                                    if("1".equals(result)){
                                        Log.i("success","success");
                                        IntentFilter filter = new IntentFilter("com.broadcast2.MY_ACTION");
                                        MyReceiver receiver=new MyReceiver();
                                        registerReceiver(receiver,filter);
                                        Intent intent=new Intent();
                                        intent.setAction("com.broadcast2.MY_ACTION");
                                        intent.putExtra("msg","注册成功！");
                                        sendBroadcast(intent);
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();

                        finish();
                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                    startActivity(intent);


                }
                break;
            }
            //自动对齐快捷键 ctrl +alt+i
            default:break;
        }
    }

    class EditChangedListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }
        @Override
        public void afterTextChanged(Editable s) {
            if(!editName.getText().toString().equals("")&&!editTel.getText().toString().equals("")&&!editCode.getText().toString().equals("")&&!editPwd.getText().toString().equals("")&&!editConfirm.getText().toString().equals("")){
                register.setBackgroundResource(R.drawable.bg_of_orange_gradient_radius);
                canClick=true;
            }else {
                register.setBackgroundResource(R.drawable.corner_gray_shape);
                canClick=false;
            }
        }
    }
}
