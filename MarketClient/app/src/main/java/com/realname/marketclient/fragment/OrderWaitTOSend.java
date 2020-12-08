package com.realname.marketclient.fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.school.marketclient.R;
import com.realname.marketclient.adapter.OrderListAdapter;
import com.realname.marketclient.bean.Order;
import com.realname.marketclient.bean.User;
import com.realname.marketclient.net.API;
import com.realname.marketclient.net.HttpUtil;
import com.realname.marketclient.utils.UserSharePreference;

import java.util.List;

/*待支付订单*/
public class OrderWaitTOSend extends Fragment {

    private View view;
    private Order order;
    private List<Order> orderList;
    private ListView order_listView;
    private User user;

    private Handler handlerAdapter=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1){

            }
        }
    };

    //加载图像
    ProgressDialog progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_order_list_all, container, false);
        progressBar= ProgressDialog.show(getActivity(),"请稍后...","获取网络资源中...");
        //从后台获取根据user_id获取order
        new Thread()
        {
            @Override
            public void run() {
                try{
                    User user = UserSharePreference.getUser_mes(getActivity());
                    int user_id=user.getUserId();
                    String RequestParm="?user_id="+user_id+"&&orderState="+1;
                     RequestParm= HttpUtil.get(API.TO_PAY, RequestParm);
                    Log.e("json",RequestParm);
                    //将json的string转换为User对象
                    Gson gson = new Gson();
                    OrderWaitTOSend.this.user = gson.fromJson(RequestParm, User.class);
                    Log.e("object", OrderWaitTOSend.this.user.toString());
                    Message message=new Message();
                    message.what=1;
                    handler.sendMessage(message);
                }catch (Exception e)
                {
                    e.printStackTrace();
                    e.printStackTrace();
                    Message message=new Message();
                    message.what=2;
                    handler.sendMessage(message);
                }
            }
        }.start();
        return view;
    }

    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            progressBar.dismiss();
            if (msg.what==1)
            {
                //找到存放订单的列表
                order_listView=view.findViewById(R.id.order_list);
//        将所有订单传入适配器中
                List<Order> orderList=user.getOrderList();
                OrderListAdapter orderListAdapter = new OrderListAdapter(getActivity(),orderList,handlerAdapter);
//         绑定到存放订单的列表中
                order_listView.setAdapter(orderListAdapter);
            }else {
                Toast.makeText(getActivity(),"加载网络资源失败！请重试！",Toast.LENGTH_SHORT).show();

            }
        }
    };
}
