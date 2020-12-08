package com.realname.marketclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.realname.marketclient.activity.OrderActivity;
import com.school.marketclient.R;
import com.realname.marketclient.bean.Order;
import com.realname.marketclient.bean.Orderitem;
import com.realname.marketclient.net.API;
import com.realname.marketclient.net.HttpUtil;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created on 2020/2/26 21:16.
 * @description 订单适配器
 */


class ViewHolder{
    //下单时间，下单状态，下单总价格
    public TextView order_down_date,order_state,order_price;
    //订单项下的多个商品
    public ListView productList;
    public TextView order_item_button;
    //适配器传递过来的视图
    View orderItemView;



    public ViewHolder(View orderItemView) {
        if (orderItemView==null)
        {
            throw new IllegalArgumentException("订单项视图返回为空");
        }
        this.orderItemView = orderItemView;
        order_down_date=orderItemView.findViewById(R.id.order_down_date);
        productList=orderItemView.findViewById(R.id.orderItemList);
        order_price=orderItemView.findViewById(R.id.order_price);
        order_state=orderItemView.findViewById(R.id.order_state);
        order_item_button=orderItemView.findViewById(R.id.order_item_button);
    }
}
public class OrderListAdapter  extends BaseAdapter {

    private LayoutInflater layoutInflater;
    private Context context;
    private List<Order>  orderList;
    private ViewHolder viewHolder;
    private Handler handler;

    public OrderListAdapter(Context context, List<Order> orderList,Handler handler) {
        this.context = context;
        this.orderList = orderList;
        Log.e("传入适配器的所有订单",orderList.toString());
//        用来找res/layout/下的xml布局文件，并且实例化
        layoutInflater=LayoutInflater.from(context);
        this.handler=handler;
    }

    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Object getItem(int i) {
        return orderList.get(i);
    }

    @Override
    public long getItemId( int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        String Order_state=null;
        //总商品数量
        int count=0;
        if(view==null)
        {
            view=layoutInflater.inflate(R.layout.order_item,null);
            //ViewHolder构造方法，绑定构件
            viewHolder=new ViewHolder(view);
            //setTag 可给控件附加存储任意类型值
            view.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) view.getTag();
        }
        //返回数据
//        总价格
        BigDecimal orderPrice = orderList.get(i).getOrderPrice();
//        物品数量
        List<Orderitem> orderitemList = orderList.get(i).getOrderitemList();
        for (Orderitem orderitem:orderitemList)
        {
//            得到每一个order_item下的商品数量
            int size = orderitem.getProductCount();
            count=count+size;
        }
        String desc_orderPrice="共"+count+"件商品"+",合计:$"+orderPrice;
        viewHolder.order_price.setText(desc_orderPrice);
//        判断订单状态
        if(orderList.get(i).getOrderState()==0)
        {
            Order_state="待支付";
            viewHolder.order_item_button.setText("请支付");
            viewHolder.order_item_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //订单ID传递过去改变订单状态
                    new Thread(){
                        @Override
                        public void run() {
                            try {
                                String s = HttpUtil.get(API.CHANGE_STATE, "?order_id=" + orderList.get(i).getOrderId());
//                                orderList.get(i).setOrderState(1);
                                Log.e("json", s);
                                Intent intent=new Intent(context, OrderActivity.class);
                                context.startActivity(intent);
                                //OrderListAdapter.this.notifyDataSetChanged();
//                                Message message = new Message();
//                                message.what=1;
//                                handler.sendMessage(message);
                            } catch (IOException e) {
                               e.printStackTrace();
                            }
                        }
                    }.start();

                }
            });
        }else if(orderList.get(i).getOrderState()==1) {
            Order_state = "待发货";
            viewHolder.order_item_button.setVisibility(View.INVISIBLE);
        }else if(orderList.get(i).getOrderState()==2) {
            Order_state = "待确认";
            viewHolder.order_item_button.setText("请确认");
            viewHolder.order_item_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //订单ID传递过去改变订单状态
                    new Thread(){
                        @Override
                        public void run() {
                            try {
                                String s = HttpUtil.get(API.CHANGE_STATE, "?order_id=" + orderList.get(i).getOrderId());
                                Intent intent=new Intent(context, OrderActivity.class);
                                context.startActivity(intent);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }.start();
                }
            });
        } else if(orderList.get(i).getOrderState()==3) {
                Order_state="待评价";
            viewHolder.order_item_button.setVisibility(View.INVISIBLE);
   /*         viewHolder.order_item_button.setText("请评价");
            viewHolder.order_item_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //订单ID传递过去改变订单状态
                    new Thread(){
                        @Override
                        public void run() {
                            Intent intent=new Intent(context, EvaluteActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putInt("OrderID",orderList.get(i).getOrderId());
                            intent.putExtras(bundle);
                            context.startActivity(intent);
                        }
                    }.start();
                }
            });*/

        }else if(orderList.get(i).getOrderState()==4) {
            Order_state="已失效";
        }


        viewHolder.order_state.setText(Order_state);
        viewHolder.order_down_date.setText(orderList.get(i).getOrderDowndate());
//        viewHolder.order_down_date.setText(DateConvert.changeDate(orderList.get(i).getOrderDowndate()));

        //传入order_item_list
        OrderItemListAdapter orderItemListAdapter = new OrderItemListAdapter(context, orderList.get(i).getOrderitemList());
        viewHolder.productList.setAdapter(orderItemListAdapter);

        //测试
        if(view==null)
        {
            Log.e(null,"适配器返回视图错误");
        }
        if(view!=null)
        {
            Log.e(null,"适配器返回视图成功");
        }
        return view;
    }
}
