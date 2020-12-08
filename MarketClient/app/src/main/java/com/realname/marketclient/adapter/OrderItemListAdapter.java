package com.realname.marketclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.realname.marketclient.activity.EvaluteActivity;
import com.school.marketclient.R;
import com.realname.marketclient.bean.Orderitem;
import com.realname.marketclient.bean.Product;
import com.realname.marketclient.net.HttpUtil;

import java.util.List;

/**
 * @author Created by xrs on 2020/2/25 16:10.
 * @description
 */
class ProductViewHolder{
    public LinearLayout product_evalute;
    public ImageView orderItemImg;
    public TextView orderItemName,order_item_money,order_item_count;
    View productItemView;
    public ProductViewHolder(View view)
    {
        if (view==null)
        {
            throw new IllegalArgumentException("ProductItemView 不能为空");
        }
        this.productItemView=view;
        orderItemImg=productItemView.findViewById(R.id.order_item_img);
        orderItemName=productItemView.findViewById(R.id.order_item_name);
        order_item_count=productItemView.findViewById(R.id.order_item_count);
        order_item_money=productItemView.findViewById(R.id.order_item_money);
        product_evalute=productItemView.findViewById(R.id.product_evalute);
    }

}
public class OrderItemListAdapter extends BaseAdapter {

    private Context context;
    private List<Orderitem> orderitemList;
    private LayoutInflater layoutInflater;
    private ProductViewHolder ProductViewHolder;

    public OrderItemListAdapter(Context context, List<Orderitem> orderitemList) {
        this.context = context;
        this.orderitemList = orderitemList;
        Log.e("订单项",orderitemList.toString());
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return orderitemList.size();
    }

    @Override
    public Object getItem(int i) {
        return orderitemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        String ProductCount,ProductPrice;

        //一个订单项一个物品
        final Product product = orderitemList.get(i).getProduct();

        if(view==null)
        {
            view=layoutInflater.inflate(R.layout.product,null);
            //ViewHolder构造方法，绑定构件
            ProductViewHolder=new ProductViewHolder(view);
            //setTag 可给控件附加存储任意类型值
            view.setTag(ProductViewHolder);
        }else{
            ProductViewHolder= (ProductViewHolder) view.getTag();
        }
        //单价
        ProductPrice="$"+product.getProductSaleprice().toString();
        ProductViewHolder.order_item_money.setText(ProductPrice);
        //数量
        ProductCount=orderitemList.get(i).getProductCount().toString();
        ProductCount="X"+ProductCount;
        ProductViewHolder.order_item_count.setText(ProductCount);
        //第几个商品
        ProductViewHolder.orderItemName.setText( product.getProductName());
        //图片
        HttpUtil.setImageResource(context,ProductViewHolder.orderItemImg,product.getProductImg1());
     //   ProductViewHolder.orderItemName.setText(orderitemList.get(i).get);

        ProductViewHolder.product_evalute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    //订单ID传递过去改变订单状态
                    new Thread(){
                        @Override
                        public void run() {
                            Intent intent=new Intent(context, EvaluteActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putString("ProductImage",product.getProductImg1());
                            bundle.putInt("ProductID",product.getProductId());
                            bundle.putString("ProductName",product.getProductName());
                            intent.putExtras(bundle);
                            context.startActivity(intent);
                        }
                    }.start();
                }
            });

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
