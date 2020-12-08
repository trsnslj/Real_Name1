package com.realname.marketclient.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.school.marketclient.R;
import com.realname.marketclient.bean.Cart;
import com.realname.marketclient.net.HttpUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CartListAdapter extends BaseAdapter{

    private List<Cart> carts=new ArrayList<>();
    private Context context;
    private Handler handler;

    public CartListAdapter( Context context, Handler handler) {

        this.context = context;
        this.handler=handler;
    }

    public void setData(List<Cart> carts){
        this.carts = carts;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(carts!=null){
            return carts.size();
        }else{
            return 0;
        }

    }
    @Override
    public Object getItem(int position) {
        return carts.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder{
       ImageView productCheck,productImg;
       TextView productName,productPrice,count;
       LinearLayout minus,plus;
    }
    ViewHolder holder;

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.cart_list_item_layout,null);
            holder=new ViewHolder();
            holder.productCheck=convertView.findViewById(R.id.product_check);
            holder.productImg=convertView.findViewById(R.id.product_img);
            holder.productName=convertView.findViewById(R.id.product_name);
            holder.productPrice=convertView.findViewById(R.id.product_price);
            holder.count=convertView.findViewById(R.id.count_view);
            holder.minus=convertView.findViewById(R.id.minus);
            holder.plus=convertView.findViewById(R.id.plus);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder) convertView.getTag();
        }
        final Cart cart = carts.get(position);
        HttpUtil.setImageResource(context,holder.productImg, cart.getProductImg());
        holder.productName.setText(cart.getProductName()+" "+cart.getProductDes());
        holder.productPrice.setText(cart.getProductSaleprice().multiply(new BigDecimal(cart.getProductNum())).toString());
        holder.count.setText(cart.getProductNum()+"");
        holder.productCheck.setOnClickListener(new View.OnClickListener() {
            Cart cart1=cart;
            ViewHolder holder1=holder;
            @Override
            public void onClick(View v) {
                cart1.setSelected(!cart1.isSelected());
                Message message=new Message();
                if(cart1.isSelected()){
                    holder1.productCheck.setImageResource(R.drawable.market_icon_checkbox_checked);
                    message.what=1;
                }else{
                    holder1.productCheck.setImageResource(R.drawable.market_icon_checkbox_default);
                    message.what=2;
                    for (Cart cart2 : carts) {
                        if(cart2.isSelected()) {
                            message.what=1;
                            break;
                        }
                    }
                }
                handler.sendMessage(message);
            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            Cart cart1=cart;
            ViewHolder holder1=holder;
            @Override
            public void onClick(View v) {
                Log.i("minus","minus");
                int count=Integer.parseInt(holder1.count.getText().toString());
                if(count==1) return;
                count--;
                cart1.setProductNum(count);
                holder1.productPrice.setText(cart1.getProductSaleprice().multiply(new BigDecimal(cart1.getProductNum())).toString());
                holder1.count.setText(count+"");
                Message message = new Message();
                message.what=3;
                handler.sendMessage(message);
            }
        });
        holder.plus.setOnClickListener(new View.OnClickListener() {
            Cart cart1=cart;
            ViewHolder holder1=holder;
            @Override
            public void onClick(View v) {
                Log.i("plus","plus");
                int count=Integer.parseInt(holder1.count.getText().toString());
                count++;
                cart1.setProductNum(count);
                holder1.productPrice.setText(cart1.getProductSaleprice().multiply(new BigDecimal(cart1.getProductNum())).toString());
                holder1.count.setText(count+"");
                Message message = new Message();
                message.what=3;
                handler.sendMessage(message);
            }
        });
        return convertView;
    }


}
