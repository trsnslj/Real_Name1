package com.realname.marketclient.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaCas;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.se.omapi.Session;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.realname.marketclient.fragment.OrderListAll;
import com.realname.marketclient.fragment.OrderWaitTOCheek;
import com.realname.marketclient.fragment.OrderWaitTOEvaluate;
import com.realname.marketclient.fragment.OrderWaitTOPay;
import com.realname.marketclient.fragment.OrderWaitTOSend;
import com.realname.marketclient.fragment.order_top;
import com.school.marketclient.R;
import com.realname.marketclient.adapter.MyViewPagerAdapter;
import com.realname.marketclient.utils.UserSharePreference;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity implements order_top.OnFragmentInteractionListener, View.OnClickListener, TabLayout.OnTabSelectedListener {

   private  ImageView order_back;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyViewPagerAdapter viewPagerAdapter;
    //TabLayout标签
    private String[] titles=new String[]{"全部","待支付","待发货","待确认","待评价"};
    private List<Fragment> fragments=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.order_bottom_fragment,new bottom_fragment())
//                .commit();
//        //初始化组件
        init();


    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.fade, R.anim.hold);
    }

    public void init() {
        order_back=findViewById(R.id.order_back);
        order_back.setOnClickListener(this);
//        头部
        tabLayout=(TabLayout)findViewById(R.id.tab_layou);
        viewPager=(ViewPager)findViewById(R.id.view_pager);
        //设置TabLayout标签的显示方式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //循环注入标签
        for (String tab:titles){
            tabLayout.addTab(tabLayout.newTab().setText(tab));
        }
        if(UserSharePreference.getUser_mes(OrderActivity.this)!=null)
        {
            //设置TabLayout点击事件
            tabLayout.setOnTabSelectedListener(this);
            fragments.add(new OrderListAll());
            fragments.add(new OrderWaitTOPay());
            fragments.add(new OrderWaitTOSend());
            fragments.add(new OrderWaitTOCheek());
            fragments.add(new OrderWaitTOEvaluate());
        }
        else{
            Toast.makeText(OrderActivity.this,"请您先登陆！",Toast.LENGTH_SHORT).show();
        }
        viewPagerAdapter=new MyViewPagerAdapter(getSupportFragmentManager(),titles,fragments);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.order_back:
                finish();
                break;
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }
    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }
}
