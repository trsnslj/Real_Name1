package com.realname.marketclient.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.school.marketclient.R;
import com.realname.marketclient.bean.Cart;
import com.realname.marketclient.bean.CartOrder;
import com.realname.marketclient.bean.Order;
import com.realname.marketclient.bean.User;
import com.realname.marketclient.net.API;
import com.realname.marketclient.net.HttpUtil;
import com.realname.marketclient.receiver.MyReceiver;
import com.realname.marketclient.utils.DateConvert;
import com.realname.marketclient.utils.UserSharePreference;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class SetOrderActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView editArea,confirm;
    private EditText editName,editTel,editAreaDetail;
    private String carts,totalPrice;
    private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_order);
        CityPickerView.getInstance().init(this);
        init();
        //final List<Cart> carts=(ArrayList<Cart>)getIntent().getSerializableExtra("carts");
        Intent intent = getIntent();
        carts = intent.getStringExtra("carts");
        totalPrice=intent.getStringExtra("totalPrice");
    }

    public void init(){
        editArea=findViewById(R.id.edt_area);
        editArea.setOnClickListener(this);
        editName=findViewById(R.id.edt_name);
        editTel=findViewById(R.id.edt_tel);
        editAreaDetail=findViewById(R.id.edt_area_detail);
        confirm=findViewById(R.id.confirm);
        confirm.setOnClickListener(this);
        back=findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edt_area:{
                //隐藏键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                //添加默认的配置，不需要自己定义
                CityConfig cityConfig = new CityConfig.Builder().build();
                CityPickerView.getInstance().setConfig(cityConfig);
                //监听选择点击事件及返回结果
                CityPickerView.getInstance().setOnCityItemClickListener(new OnCityItemClickListener() {
                    @Override
                    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                        String res="";
                        //省份
                        if (province != null) {
                            res+=province.toString();
                        }
                        //城市
                        if (city != null) {
                            res+=city.toString();
                        }
                        //地区
                        if (district != null) {
                            res+=district.toString();
                        }
                        editArea.setText(res);
                    }
                    @Override
                    public void onCancel() {
                        ToastUtils.showLongToast(SetOrderActivity.this, "已取消");
                    }
                });
                CityPickerView.getInstance().showCityPicker( );
                break;
            }
            case R.id.confirm:{
                if(editTel.getText().toString().equals("")||editAreaDetail.getText().toString().equals("")||editArea.getText().toString().equals("")||editName.getText().toString().equals("")){
                    Toast.makeText(SetOrderActivity.this,"请完整填写收货信息！！！",Toast.LENGTH_SHORT).show();
                    break;
                }
                User user_mes = UserSharePreference.getUser_mes(SetOrderActivity.this);
                if(user_mes==null){
                    Toast.makeText(SetOrderActivity.this,"请检查是否登录！！！",Toast.LENGTH_SHORT).show();
                }
                Integer userId = user_mes.getUserId();
                final Order order = new Order();
                order.setUserId(userId);
               // order.setOrderDowndate(new Date());
                order.setOrderDowndate(DateConvert.changeDate((new Date())));
                order.setOrderState(0);
                order.setOrderAddress(editArea.getText().toString()+editAreaDetail.getText().toString());
                order.setOrderName(editName.getText().toString());
                order.setOrderTel(editTel.getText().toString());
                order.setPostMethod(1);
                order.setOrderPrice(new BigDecimal(totalPrice));
                new Thread(){
                    @Override
                    public void run() {
                        Gson gson = new Gson();
             //                   .setDateFormat("yyyy-MM-dd HH:mm:ss")
                        //                  .create();
                        try {
                            List<Cart> carts1 = gson.fromJson(carts, new TypeToken<List<Cart>>(){}.getType());
                            CartOrder cartOrder = new CartOrder(carts1, order);
                            String s = HttpUtil.post(API.INSERT_ORDER, cartOrder);
                            if("1".equals(s)){
                                Intent intent = new Intent(SetOrderActivity.this, OrderActivity.class);
                                startActivity(intent);
                            }else{
                                //Toast.makeText(SetOrderActivity.this,"网络异常！请重试！",Toast.LENGTH_SHORT).show();
                                IntentFilter filter = new IntentFilter("com.broadcast2.MY_ACTION");
                                MyReceiver receiver=new MyReceiver();
                                registerReceiver(receiver,filter);
                                Intent intent=new Intent();
                                intent.setAction("com.broadcast2.MY_ACTION");
                                intent.putExtra("msg","网络请求异常！请重试！");
                                sendBroadcast(intent);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
                break;
            }
            case R.id.back:{
                finish();
                break;
            }
        }
    }
}
