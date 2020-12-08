package com.realname.marketclient.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.school.marketclient.R;
import com.realname.marketclient.activity.ShowProductActivity;
import com.realname.marketclient.bean.Product;
import com.realname.marketclient.net.HttpUtil;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;


public class ProductImgFragment extends Fragment implements OnBannerListener {

    private Banner banner;
    private ArrayList<String> list_path;
    private View view;
    private Product product;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_product_img, container, false);
        ShowProductActivity activity = (ShowProductActivity)getActivity();
        int productId = activity.getProductId();
        product=activity.getProduct();
        Log.i("product2222111",product.toString());
        initView();
        return view;
    }

    private void initData(){
        if(product.getProductImg1()!=null&&product.getProductImg1().length()>0){
            list_path.add(HttpUtil.IMAGE_PATH+product.getProductImg1());
        }
        if(product.getProductImg2()!=null&&product.getProductImg2().length()>0){
            list_path.add(HttpUtil.IMAGE_PATH+product.getProductImg2());
        }
        if(product.getProductImg3()!=null&&product.getProductImg3().length()>0){
            list_path.add(HttpUtil.IMAGE_PATH+product.getProductImg3());
        }
    }
    private void initView() {
        banner = view.findViewById(R.id.banner);
        list_path = new ArrayList<>();
        initData();
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        banner.setImageLoader(new ProductImgFragment.MyLoader());
        banner.setBannerAnimation(Transformer.Default);
        banner.setDelayTime(3000);
        banner.isAutoPlay(true);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImages(list_path)
                .setOnBannerListener(this)
                .start();
    }

    @Override
    public void OnBannerClick(int position) {
        Toast.makeText(getContext(), "你点了第" + (position + 1) + "张轮播图", Toast.LENGTH_SHORT).show();
    }

    //自定义的图片加载器
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context.getApplicationContext())
                    .load((String) path)
                    .into(imageView);
        }
    }
}
