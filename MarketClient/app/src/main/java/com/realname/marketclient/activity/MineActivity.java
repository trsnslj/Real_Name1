package com.realname.marketclient.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.realname.marketclient.fragment.bottom_fragment;
import com.school.marketclient.R;
import com.realname.marketclient.bean.User;
import com.realname.marketclient.receiver.FinishReceiver;
import com.realname.marketclient.utils.UserSharePreference;


public class MineActivity extends AppCompatActivity  implements View.OnClickListener {
    private LinearLayout AboutMe,MyEvaluate,Cancel,MyOrder;
    private TextView To_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        //添加底部fragment
        getSupportFragmentManager().beginTransaction()
                .add(R.id.mime_bottom_fragment,new bottom_fragment())
                .commit();

        //初始化组件
        init();
        User user_mes = UserSharePreference.getUser_mes(this);
        if(user_mes!=null)
        {
            String username=user_mes.getUserName();
            To_login.setText(username);
        }
    }
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    private void init() {
        AboutMe=findViewById(R.id.AboutMe);
        To_login=findViewById(R.id.To_login);
        MyEvaluate=findViewById(R.id.MyCart);
        Cancel=findViewById(R.id.cancel);
        MyOrder=findViewById(R.id.MyOrder);


        MyOrder.setOnClickListener(this);
        AboutMe.setOnClickListener(this);
       To_login.setOnClickListener(this);
        MyEvaluate.setOnClickListener(this);
        Cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.AboutMe:{
                Intent intent=new Intent(this,AboutMeActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.To_login:{
                Intent intent=new Intent(this,LoginActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.MyOrder:{
                Intent intent=new Intent(this,OrderActivity.class);
                startActivity(intent);
                break;
            }

            case R.id.MyCart:{
                Intent intent=new Intent(this,CartActivity.class);
                startActivity(intent);
                break;
            }


            case R.id.cancel:
                //清除SharePreferences文件
                UserSharePreference.ClearData(this);
                //测试通知
                IntentFilter filter = new IntentFilter("com.broadcast2.NOTIFY");
                FinishReceiver receiver = new FinishReceiver();
                registerReceiver(receiver,filter);

                Intent intent4=new Intent();
                intent4.setAction("com.broadcast2.NOTIFY");
                sendBroadcast(intent4);
                Intent intent3=new Intent(this,MineActivity.class);
                startActivity(intent3);
                break;
        }


    }
}
