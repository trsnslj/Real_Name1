package com.realname.marketclient.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.realname.marketclient.fragment.ProductCommentFragment;
import com.realname.marketclient.fragment.ProductInfoFragment;
import com.school.marketclient.R;
import com.realname.marketclient.adapter.MyViewPagerAdapter;
import com.realname.marketclient.bean.Comment;
import com.realname.marketclient.bean.Product;
import com.realname.marketclient.net.API;
import com.realname.marketclient.net.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShowProductActivity extends AppCompatActivity implements  TabLayout.OnTabSelectedListener, View.OnClickListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyViewPagerAdapter viewPagerAdapter;
    //TabLayout标签
    private String[] titles=new String[]{"详情","评价"};
    private List<Fragment> fragments=new ArrayList<>();
    private int productId;
    private Product product;
    private List<Comment> comments;
    private ProgressDialog progressBar;
    private ImageView ShowProductReturn,ShowProductCart;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            progressBar.dismiss();
            if(msg.what==1){
                fragments.add(new ProductInfoFragment());
                fragments.add(new ProductCommentFragment());
                viewPagerAdapter=new MyViewPagerAdapter(getSupportFragmentManager(),titles,fragments);
                viewPager.setAdapter(viewPagerAdapter);
                tabLayout.setupWithViewPager(viewPager);
            }else{
                Toast.makeText(ShowProductActivity.this,"加载网络资源失败！请重试！",Toast.LENGTH_SHORT).show();
            }
        }
    };

    public List<Comment> getComments() {
        return comments;
    }

    public int getProductId() {
        return productId;
    }

    public Product getProduct() {
        return product;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_product);
        Intent intent = this.getIntent();
        productId = intent.getIntExtra("productId", 1);
        init();
    }

    private void init(){
        final Gson gson = new GsonBuilder()
                              .setDateFormat("MMM dd, yyyy, HH:mm:ss aa")
                              .create();
        tabLayout= findViewById(R.id.tab_layou);
        viewPager= findViewById(R.id.view_pager);
        ShowProductCart=findViewById(R.id.ShowProduct_Cart);
        ShowProductReturn=findViewById(R.id.ShowProduct_Return);
        ShowProductReturn.setOnClickListener(this);
        ShowProductCart.setOnClickListener(this);

        //设置TabLayout标签的显示方式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //循环注入标签
        for (String tab:titles){
            tabLayout.addTab(tabLayout.newTab().setText(tab));
        }
        //设置TabLayout点击事件
        tabLayout.addOnTabSelectedListener(this);
        progressBar= ProgressDialog.show(ShowProductActivity.this,"请稍后...","获取网络资源中...");
        new Thread(){
            @Override
            public void run() {
                try {
                    String s = HttpUtil.get(API.GET_ONE_PRODUCT, "?productId=" + productId);
                    product = gson.fromJson(s, Product.class);
                    String s1 = HttpUtil.get(API.GET_COMMENTS, "?productId=" + productId);
                    //将冲过来的json字符串转变为对象数组
                    comments=gson.fromJson(s1,new TypeToken<List<Comment>>(){}.getType());
                    Message message=new Message();
                    message.what=1;
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                    Message message=new Message();
                    message.what=2;
                    handler.sendMessage(message);
                }
            }
        }.start();
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
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.ShowProduct_Return:
                finish();
                break;
            case R.id.ShowProduct_Cart:
                Intent intent=new Intent(this,CartActivity.class);
                startActivity(intent);
                break;
            default:break;
        }
    }
}
