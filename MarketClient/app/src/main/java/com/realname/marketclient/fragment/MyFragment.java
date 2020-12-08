package com.realname.marketclient.fragment;

import android.content.Intent;
import android.content.IntentFilter;
import android.view.View;

import com.school.marketclient.R;
import com.realname.marketclient.activity.AboutMeActivity;
import com.realname.marketclient.activity.BaseFragment;
import com.realname.marketclient.activity.CartActivity;
import com.realname.marketclient.activity.LoginActivity;
import com.realname.marketclient.activity.OrderActivity;
import com.realname.marketclient.activity.PasswordResetActivity;
import com.realname.marketclient.receiver.FinishReceiver;
import com.realname.marketclient.utils.UserSharePreference;

public class MyFragment  extends BaseFragment implements View.OnClickListener{


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.MyCart:{
                Intent intent=new Intent(getActivity(),CartActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.MyOrder:{
                Intent intent=new Intent(getActivity(), OrderActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.sb_xfamount:{
//                Intent intent=new Intent(this, CartActivity.class);
//                startActivity(intent);
                break;
            }
            case R.id.sb_updatepwd:{
                Intent intent=new Intent(getActivity(), PasswordResetActivity.class);
                startActivity(intent);

                break;
            }
            case R.id.AboutMe:{
                Intent intent=new Intent(getActivity(), AboutMeActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.cancel:{
                //清除SharePreferences文件
                UserSharePreference.ClearData(getActivity());
                //测试通知
                IntentFilter filter = new IntentFilter("com.broadcast2.NOTIFY");
                FinishReceiver receiver = new FinishReceiver();
                getActivity().registerReceiver(receiver,filter);

                Intent intent4=new Intent();
                intent4.setAction("com.broadcast2.NOTIFY");
                getActivity().sendBroadcast(intent4);
//                Intent intent3=new Intent(getActivity(), MineActivity.class);
//                startActivitytActivity(intent3);
                break;
            }
            case R.id.To_login:{
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_my;
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
    protected void initView() {
        super.initView();
        getView().findViewById(R.id.MyCart).setOnClickListener(this);//我的购物车
        getView().findViewById(R.id.MyOrder).setOnClickListener(this);//我的订单
        getView().findViewById(R.id.sb_xfamount).setOnClickListener(this);//消费额
        getView().findViewById(R.id.sb_updatepwd).setOnClickListener(this);//修改密码
        getView().findViewById(R.id.AboutMe).setOnClickListener(this);//关于我们
        getView().findViewById(R.id.cancel).setOnClickListener(this);//关于我们
        getView().findViewById(R.id.To_login).setOnClickListener(this);//关于我们
    }


    @Override
    protected void initData() {
        super.initData();
    }





}
