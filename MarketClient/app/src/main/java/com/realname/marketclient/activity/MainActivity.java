package com.realname.marketclient.activity;



//import android.app.Fragment;
//import android.app.FragmentManager;
//import android.app.FragmentTransaction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.realname.marketclient.fragment.HomeFragment;
import com.realname.marketclient.fragment.MyFragment;
import com.realname.marketclient.fragment.OrderFragment;
import com.school.marketclient.R;
import com.realname.marketclient.adapter.ProductListAdapter;
import com.realname.marketclient.bean.Product;
import com.realname.marketclient.bean.User;
import com.realname.marketclient.utils.UserSharePreference;

import java.util.List;


public class MainActivity extends AppCompatActivity implements  BottomNavigationView.OnNavigationItemSelectedListener {

    //适配商品
    private List<Product> products;
    private ProductListAdapter adapter;
    private ListView listView;
    private BottomNavigationView mBottomNavigationView;
    private Fragment mFrag_home;
    private Fragment mFrag_order ;
    private Fragment mFrag_my ;
    private FragmentManager fragmentManager;



    //顶部菜单按钮
    private ImageView main_back,main_order;
//    //开场动画
//    private ImageView main_anim;
    //加载图像
    ProgressDialog progressBar;


//    @SuppressLint("HandlerLeak")
//    private Handler handler=new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            progressBar.dismiss();
//            if (msg.what==1)
//            {
//                listView=(ListView)findViewById(R.id.productList);
//                adapter=new ProductListAdapter(products,MainActivity.this);
//                listView.setAdapter(adapter);
//            }else {
//                Toast.makeText(MainActivity.this,"加载网络资源失败！请重试！",Toast.LENGTH_SHORT).show();
//
//            }
//        }
//    };




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //添加底部fragment
//        getSupportFragmentManager().beginTransaction()
//                .add(R.id.bottom_fragment,new bottom_fragment())
//                .commit();
//        //初始化组件
        init();
//        Log.i("products11111",products.toString());


//        progressBar= ProgressDialog.show(MainActivity.this,"请稍后...","获取网络资源中...");
//        new Thread()
//        {
//            @Override
//            public void run() {
//                try{
//                    String s = HttpUtil.get(API.SHOW_PRODUCT, "");
//                    Log.e("123145",s);
//                    //将json的string转换为list对象
//                    Gson gson = new Gson();
//                    products = gson.fromJson(s, new TypeToken<List<Product>>(){}.getType());
//                    Log.i("producta",products.toString());
//                    Message message=new Message();
//                    message.what=1;
//                    handler.sendMessage(message);
//                }catch (Exception e)
//                {
//                    e.printStackTrace();
//                    Message message=new Message();
//                    message.what=2;
//                    handler.sendMessage(message);
//                }
//            }
//        }.start();
    }

    //切换Activity动画
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }



//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
////        switch (item.getItemId()) {
////            case R.id.index:
////                Intent intent = new Intent(this, MainActivity.class);
////                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////                startActivity(intent);
////                return true;
////        }
////        return super.onOptionsItemSelected(item);
//    }

    public void init(){
        User user_mes = UserSharePreference.getUser_mes(MainActivity.this);
        if(user_mes!=null)
        {
            Log.i("zpx",user_mes.toString());
        }
//        main_back=findViewById(R.id.main_back);
//        main_back.setOnClickListener(this);
//        main_order=findViewById(R.id.main_order);
//        main_order.setOnClickListener(this);
        fragmentManager = getSupportFragmentManager();
        setTabSelection(0);
        mBottomNavigationView=(BottomNavigationView)findViewById(R.id.bv_home_navigation);
        mBottomNavigationView.setItemIconTintList(null);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);

    }

    private void getAllProduct() {


    }

//    @Override
//    public void onClick(View view) {
////        switch (view.getId())
////        {
////            case R.id.main_back:
////                finish();
////                break;
////            case R.id.main_order:
////                Intent intent=new Intent(this,CartActivity.class);
////                startActivity(intent);
////                break;
////            default:break;
////        }
//    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_home:
                //首页
                Toast.makeText(MainActivity.this,"首页",Toast.LENGTH_LONG).show();
                setTabSelection(0);
                return true;
            case R.id.home_found:
              //  订单
                Toast.makeText(MainActivity.this,"订单",Toast.LENGTH_LONG).show();
                setTabSelection(1);
                return true;
            case R.id.home_message:
                //我的
                Toast.makeText(MainActivity.this,"我的",Toast.LENGTH_LONG).show();
                setTabSelection(2);
                return true;
            default:
                break;
        }
        return false;
    }







    private void setTabSelection(int i){
        FragmentTransaction transaction =fragmentManager.beginTransaction();
        hideAllFragment(transaction);
        switch (i){
            case 0:{
                if(mFrag_home==null){
                    mFrag_home=new HomeFragment();
                    transaction.add(R.id.main_framentlayout,mFrag_home);
                }else {
                    transaction.show(mFrag_home);
                }
                break;
            }
            case 1:{
                if( mFrag_order==null) {
                    mFrag_order = new OrderFragment();
                    transaction.add(R.id.main_framentlayout, mFrag_order);
                }else {
                    transaction.show(mFrag_order);
                }
                break;
            }
            case 2:{
                if( mFrag_my==null) {
                    mFrag_my = new MyFragment();
                    transaction.add(R.id.main_framentlayout, mFrag_my);
                }else {
                    transaction.show(mFrag_my);
                }
                break;
            }
            default:break;
        }
        transaction.commit();


    }

    /**
     * 隐藏所有的Fragment
     */
    private void hideAllFragment(FragmentTransaction transaction) {
        if(mFrag_home!=null){
            transaction.hide(mFrag_home);
        }
        if(mFrag_order!=null){
            transaction.hide(mFrag_order);
        }
        if(mFrag_my!=null){
            transaction.hide(mFrag_my);
        }
    }




}
