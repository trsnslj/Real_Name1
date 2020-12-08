package com.realname.marketclient.activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.school.marketclient.R;
import com.realname.marketclient.bean.Comment;
import com.realname.marketclient.net.API;
import com.realname.marketclient.net.HttpUtil;
import com.realname.marketclient.receiver.MyReceiver;
import com.realname.marketclient.utils.UserSharePreference;

import java.io.IOException;
import java.util.Date;

public class EvaluteActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView productImage,evaluteBack;
    private TextView evaluteCheck,productname;
    private EditText evaluteContext;
    private  int user_id,product_id ;
    private String productImageName;
    private String ProductName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evalute);
        init();
        //得到
        getResource();
        //载入传过来的产品图片
        HttpUtil.setImageResource(this,productImage,productImageName);
        productname.setText(ProductName);
    }


    private void getResource() {
        Intent intent=this.getIntent();
        Bundle bundle = intent.getExtras();
         product_id = bundle.getInt("ProductID");
         productImageName=bundle.getString("ProductImage");
        ProductName=bundle.getString("ProductName");
        user_id=UserSharePreference.getUser_mes(this).getUserId();
        Log.i("111111111111",String.valueOf(product_id ));
    }

    private void init() {
        productImage=findViewById(R.id.evalute_product_img);
        productname=findViewById(R.id.evalute_product_name);
        evaluteBack=findViewById(R.id.evalute_back);
        evaluteContext=findViewById(R.id.evaluate_context);
        evaluteCheck=findViewById(R.id.evalute_check);

        evaluteBack.setOnClickListener(this);
        evaluteCheck.setOnClickListener(this);
    }

    //切换Activity动画
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.evalute_back:
                this.finish();
                break;
            case R.id.evalute_check:
//               给后台发送user_id,product_id,context
                Date date=new Date();
//                DateConvert.changeDate(date);
                String contextText = String.valueOf(evaluteContext.getText());
                final Comment comment = new Comment(user_id, contextText, date, product_id);
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            String result = HttpUtil.post(API.ADD_COMMENT, comment);
                            if("1".equals(result))
                            {
                                IntentFilter filter = new IntentFilter("com.broadcast2.MY_ACTION");
                                MyReceiver receiver=new MyReceiver();
                                registerReceiver(receiver,filter);
                                Intent intent=new Intent();
                                intent.setAction("com.broadcast2.MY_ACTION");
                                intent.putExtra("msg","感谢您的评论！");
                                sendBroadcast(intent);
                                Intent intent2=new Intent(EvaluteActivity.this,OrderActivity.class);
                                startActivity(intent2);
                                Log.i("Comment",comment.toString());
                            }else {
                                IntentFilter filter = new IntentFilter("com.broadcast2.MY_ACTION");
                                MyReceiver receiver=new MyReceiver();
                                registerReceiver(receiver,filter);
                                Intent intent=new Intent();
                                intent.setAction("com.broadcast2.MY_ACTION");
                                intent.putExtra("msg","网络异常！请重试");
                                sendBroadcast(intent);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                break;
                default:break;
        }
    }
}
