package com.realname.marketclient.fragment;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.school.marketclient.R;
import com.realname.marketclient.activity.CartActivity;
import com.realname.marketclient.activity.SetOrderActivity;
import com.realname.marketclient.activity.ShowProductActivity;
import com.realname.marketclient.bean.Cart;
import com.realname.marketclient.bean.Product;
import com.realname.marketclient.bean.User;
import com.realname.marketclient.net.API;
import com.realname.marketclient.net.HttpUtil;
import com.realname.marketclient.receiver.MyReceiver;
import com.realname.marketclient.utils.UserSharePreference;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class ProductInfoFragment extends Fragment  implements View.OnClickListener {
    private View view;
    private LinearLayout minus;
    private LinearLayout plus;
    private TextView countView,buy;
    private TextView productDes,productSaleprice,productRealprice,commentNum,addCart;
    private int count;
    private ImageView toCart;
    private int productId;
    private Product product;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_product_info, container, false);
        init();
        return view;
    }

    public void init(){
        minus= view.findViewById(R.id.minus);
        plus= view.findViewById(R.id.plus);
        countView= view.findViewById(R.id.count_view);
        toCart=view.findViewById(R.id.toCart);
        toCart.setOnClickListener(this);
        minus.setOnClickListener(this);
        plus.setOnClickListener(this);
        buy=view.findViewById(R.id.btn_buy);
        buy.setOnClickListener(this);
        ShowProductActivity activity = (ShowProductActivity)getActivity();
        productId = activity.getProductId();
        product=activity.getProduct();
        productDes=view.findViewById(R.id.product_des);
        productSaleprice=view.findViewById(R.id.product_saleprice);
        productRealprice=view.findViewById(R.id.product_realprice);
        commentNum=view.findViewById(R.id.comment_num);
        addCart=view.findViewById(R.id.add_to_cart);
        countView=view.findViewById(R.id.count_view);
        productDes.setText(product.getProductName()+" "+product.getProductDes());
        productSaleprice.setText(product.getProductSaleprice().toString());
        productRealprice.setText(product.getProductRealprice().toString());
        commentNum.setText("用户点评("+activity.getComments().size()+")");
        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        Cart cart = new Cart(productId, Integer.parseInt(countView.getText().toString()));
                        User user_mes = UserSharePreference.getUser_mes(getActivity());
                        IntentFilter filter = new IntentFilter("com.broadcast2.MY_ACTION");
                        MyReceiver receiver=new MyReceiver();
                        getActivity().registerReceiver(receiver,filter);
                        Intent intent=new Intent();
                        intent.setAction("com.broadcast2.MY_ACTION");
                        if(user_mes==null){
                            intent.putExtra("msg","添加失败！请检查是否登录！");
                            getActivity().sendBroadcast(intent);
                            return;
                        }
                        try {
                            String result = HttpUtil.get(API.INSERT_CART,"?userId="+user_mes.getUserId()+"&&productId="+cart.getProductId()+"&&productNum="+cart.getProductNum());
                            if("1".equals(result)){
                                intent.putExtra("msg","添加购物车成功！");
                            }else{
                                intent.putExtra("msg","添加失败！请检查网络！");
                            }
                            getActivity().sendBroadcast(intent);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.minus:{
                count=Integer.parseInt(countView.getText().toString());
                if(count>1) count--;
                countView.setText(count+"");
                break;
            }
            case R.id.plus:{
                count=Integer.parseInt(countView.getText().toString());
                count++;
                countView.setText(count+"");
                break;
            }
            case R.id.toCart:{
                Log.i("into","into");
                User user_mes = UserSharePreference.getUser_mes(getActivity());
                if(user_mes==null){
                    Toast.makeText(getActivity(),"请检查是否登陆！",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getActivity(), CartActivity.class);
                getActivity().startActivity(intent);
                break;
            }
            case R.id.btn_buy:{
                User user_mes = UserSharePreference.getUser_mes(getActivity());
                if(user_mes==null){
                    Toast.makeText(getActivity(),"购买失败！请检查是否登录！",Toast.LENGTH_SHORT).show();
                    return;
                }
                Cart cart = new Cart(user_mes.getUserId(), productId, Integer.parseInt(countView.getText().toString()), true,product.getProductSaleprice());
                List<Cart> carts=new ArrayList<>();
                carts.add(cart);
                Intent intent = new Intent(getActivity(), SetOrderActivity.class);
                Gson gson = new Gson();
                intent.putExtra("carts",gson.toJson(carts));
                intent.putExtra("totalPrice",product.getProductSaleprice().multiply(new BigDecimal(countView.getText().toString())).toString());
                startActivity(intent);
            }
        }
    }
}
