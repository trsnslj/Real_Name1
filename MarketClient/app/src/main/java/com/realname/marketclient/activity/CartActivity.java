package com.realname.marketclient.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.school.marketclient.R;
import com.realname.marketclient.adapter.CartListAdapter;
import com.realname.marketclient.bean.Cart;
import com.realname.marketclient.bean.User;
import com.realname.marketclient.net.API;
import com.realname.marketclient.net.HttpUtil;
import com.realname.marketclient.receiver.MyReceiver;
import com.realname.marketclient.utils.UserSharePreference;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    private ListView cartsList;
    private List<Cart> carts;
    private ProgressDialog progressBar;
    private TextView delBtn,buyBtn,totalPrice;
    private ImageView back;
    private boolean canClick;
    private CartListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        init();
    }
    private Handler handlerAdapterMessage=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1){
                delBtn.setBackgroundColor(getResources().getColor(R.color.red));
                buyBtn.setBackgroundResource(R.drawable.bg_of_orange_gradient_radius);
                canClick=true;
            }else if (msg.what==2){
                delBtn.setBackgroundResource(R.drawable.corner_gray_shape);
                buyBtn.setBackgroundResource(R.drawable.corner_gray_shape);
                canClick=false;
            }
            BigDecimal total = new BigDecimal(0);
            for (Cart cart : carts) {
                if(cart.isSelected()){
                    total=total.add(cart.getProductSaleprice().multiply(new BigDecimal(cart.getProductNum())));
                }
            }
            totalPrice.setText(total.toString());
        }
    };
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            progressBar.dismiss();
            if(msg.what==1){
                adapter = new CartListAdapter( getApplicationContext(),handlerAdapterMessage);
                cartsList.setAdapter(adapter);
            }else{
                Toast.makeText(CartActivity.this,"加载网络资源失败！请重试！",Toast.LENGTH_SHORT).show();
            }
        }
    };

    public void init() {
        cartsList = findViewById(R.id.cart_list);
        buyBtn=findViewById(R.id.btn_buy);
        delBtn=findViewById(R.id.btn_del);
        buyBtn=findViewById(R.id.btn_buy);
        totalPrice=findViewById(R.id.total_price);
        back=findViewById(R.id.cart_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        progressBar= ProgressDialog.show(CartActivity.this,"请稍后...","获取网络资源中...");
        new Thread() {
            @Override
            public void run() {
                try {
                    User user = UserSharePreference.getUser_mes(CartActivity.this);
                    if(user==null){
                        //Toast.makeText(CartActivity.this,"请您先登陆！",Toast.LENGTH_SHORT).show();
                        IntentFilter filter = new IntentFilter("com.broadcast2.MY_ACTION");
                        MyReceiver receiver=new MyReceiver();
                        registerReceiver(receiver,filter);
                        Intent intent=new Intent();
                        intent.setAction("com.broadcast2.MY_ACTION");
                        intent.putExtra("msg","请您先登陆！");
                        sendBroadcast(intent);
                        progressBar.dismiss();
                        return;
                    }
                    int user_id=user.getUserId();
                    String s = HttpUtil.get(API.GET_CARTS, "?userId="+user_id);
                    Log.i("carts",s);
                    Gson gson = new Gson();
                    carts = gson.fromJson(s, new TypeToken<List<Cart>>() {
                    }.getType());
                    Message message=new Message();
                    message.what=1;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                    Message message=new Message();
                    message.what=2;
                    handler.sendMessage(message);
                }
            }
        }.start();
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!canClick){
                    Toast.makeText(CartActivity.this,"请选择要删除的商品",Toast.LENGTH_SHORT).show();
                    return;
                }
                final Iterator<Cart> iterator = carts.iterator();
                Log.i("into","into");
                while(iterator.hasNext()){
                    final Cart next = iterator.next();
                    if(next.isSelected()) {
                        new Thread(){
                            @Override
                            public void run() {
                                try {
                                    HttpUtil.get(API.DELETE_CART,"?cartId="+ next.getCartId());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();
                        iterator.remove();
                    }

                }
                Message message = new Message();
                message.what=2;
                cartsList.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                handlerAdapterMessage.sendMessage(message);
            }
        });
        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!canClick){
                    Toast.makeText(CartActivity.this,"请选择要购买的商品",Toast.LENGTH_SHORT).show();
                    return;
                }
                //将选择的商品传入填写信息的页面
                Intent intent = new Intent(CartActivity.this, SetOrderActivity.class);
                Gson gson = new Gson();
                intent.putExtra("carts",gson.toJson(carts));
                intent.putExtra("totalPrice",totalPrice.getText().toString());
                startActivity(intent);
            }
        });
    }
}
