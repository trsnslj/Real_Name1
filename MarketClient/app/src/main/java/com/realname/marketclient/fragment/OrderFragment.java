package com.realname.marketclient.fragment;

import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.school.marketclient.R;
import com.realname.marketclient.activity.BaseFragment;
import com.realname.marketclient.adapter.MyViewPagerAdapter;
import com.realname.marketclient.utils.UserSharePreference;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment  extends BaseFragment implements order_top.OnFragmentInteractionListener, View.OnClickListener, TabLayout.OnTabSelectedListener{

    private  ImageView order_back;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyViewPagerAdapter viewPagerAdapter;
    //TabLayout标签
    private String[] titles=new String[]{"全部","待支付","待发货","待确认","待评价"};
    private List<Fragment> fragments=new ArrayList<>();



    @Override
    public void onClick(View v) {

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            initView();
            initData();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initView() {
        super.initView();
        order_back=getActivity().findViewById(R.id.order_back);
        order_back.setOnClickListener(this);
//        头部
        tabLayout=(TabLayout)getActivity().findViewById(R.id.tab_layou);
        viewPager=(ViewPager)getActivity().findViewById(R.id.view_pager);
        //设置TabLayout标签的显示方式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //循环注入标签
        for (String tab:titles){
            tabLayout.addTab(tabLayout.newTab().setText(tab));
        }
        if(UserSharePreference.getUser_mes(getActivity())!=null)
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
            Toast.makeText(getActivity(),"请您先登陆！",Toast.LENGTH_SHORT).show();
        }
        viewPagerAdapter=new MyViewPagerAdapter(getActivity().getSupportFragmentManager(),titles,fragments);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
       ;
    }

    @Override
    protected void initData() {
        super.initData();



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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}