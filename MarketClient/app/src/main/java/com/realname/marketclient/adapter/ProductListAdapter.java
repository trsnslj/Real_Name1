package com.realname.marketclient.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.realname.marketclient.activity.ShowProductActivity;
import com.school.marketclient.R;
import com.realname.marketclient.bean.Product;
import com.realname.marketclient.net.HttpUtil;

import java.util.ArrayList;
import java.util.List;

public class ProductListAdapter extends BaseAdapter {

    private List<Product> products=new ArrayList<>();
    private Context context;

    public ProductListAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<Product> products){
        this.products = products;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(products!=null){
            return products.size();
        }else {
            return 0;
        }
//        int count=products.size();
//        int realCount=0;
//        if(count%2==0) realCount=count/2;
//        else realCount=count/2+1;

    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder{
        LinearLayout product1;
        ImageView product1Img;
        TextView product1Name;
        TextView product1Saleprice;
        TextView product1Realprice;
        ImageView product1Buy;
        LinearLayout product2;
        ImageView product2Img;
        TextView product2Name;
        TextView product2Saleprice;
        TextView product2Realprice;
        ImageView product2Buy;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.product_list_item_layout,null);
            holder=new ViewHolder();
            init(position,holder,convertView);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        HttpUtil.setImageResource(context,holder.product1Img,products.get(position).getProductImg1());
        holder.product1Name.setText(products.get(position).getProductName());
        holder.product1Saleprice.setText(products.get(position).getProductSaleprice().toString());
        holder.product1Realprice.setText(products.get(position).getProductRealprice().toString());
//        if(position*2+1==products.size()){
//            holder.product2Buy.setVisibility(View.GONE);
//        }else{
//            HttpUtil.setImageResource(context,holder.product2Img,products.get(position*2+1).getProductImg1());
//            holder.product2Name.setText(products.get(position*2+1).getProductName());
//            holder.product2Saleprice.setText(products.get(position*2+1).getProductSaleprice().toString());
//            holder.product2Realprice.setText(products.get(position*2+1).getProductRealprice().toString());
//        }
        return convertView;
    }

    public void init(final int position, ViewHolder holder, View convertView){
        holder.product1=(LinearLayout)convertView.findViewById(R.id.product1);
        holder.product1Img=(ImageView)convertView.findViewById(R.id.product1Img);
        holder.product1Name=(TextView) convertView.findViewById(R.id.product1Name);
        holder.product1Saleprice=(TextView)convertView.findViewById(R.id.product1Saleprice);
        holder.product1Realprice=(TextView)convertView.findViewById(R.id.product1Realprice);
        holder.product1Realprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
//        holder.product2=(LinearLayout)convertView.findViewById(R.id.product2);
//        holder.product2Img=(ImageView)convertView.findViewById(R.id.product2Img);
//        holder.product2Name=(TextView) convertView.findViewById(R.id.product2Name);
//        holder.product2Saleprice=(TextView)convertView.findViewById(R.id.product2Saleprice);
//        holder.product2Realprice=(TextView)convertView.findViewById(R.id.product2Realprice);
//        holder.product2Realprice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        holder.product1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowProductActivity.class);
                intent.putExtra("productId",products.get(position).getProductId());
                context.startActivity(intent);
            }
        });
//        holder.product2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, ShowProductActivity.class);
//                intent.putExtra("productId",products.get(position*2+1).getProductId());
//                context.startActivity(intent);
//            }
//        });
    }
}
