package com.realname.model.model;

import com.realname.marketclient.bean.Cart;
import com.realname.marketclient.bean.Product;
import com.realname.persenter.ProductShowPresenterLisener;
import com.realname.utils.RetrofitManager;
import com.realname.model.ProductApi;
import com.realname.model.ProductShowModelLisener;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ProductShowModelImpl implements ProductShowModelLisener {

    private ProductShowPresenterLisener productShowPresenterLisener;
    public  void setProductShowPresenterLisener(ProductShowPresenterLisener lisener){
        productShowPresenterLisener=lisener;
    }

    public static ProductShowModelImpl INSTANCE;
    public static synchronized ProductShowModelImpl getInstance(){
        if(INSTANCE==null){
            INSTANCE=new ProductShowModelImpl();
        }
        return INSTANCE;
    }

    @Override
    public void productShow() {

        try{
            RetrofitManager.getDefault().create(ProductApi.class).productShow()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<Product>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(List<Product> productShowBean) {
                            if(productShowPresenterLisener!=null){
                                productShowPresenterLisener.onProductShowSuccess(productShowBean);
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            if(productShowPresenterLisener!=null){
                                productShowPresenterLisener.onProductShowFailed(e.getMessage().toString());
                            }
                        }

                        @Override
                        public void onComplete() {

                        }
                    });


        }catch (Exception e){
            e.printStackTrace();
            if(productShowPresenterLisener!=null){
                productShowPresenterLisener.onProductShowFailed(e.getMessage().toString());
            }
        }



    }


    //查询订单
    @Override
    public void cartShow(int userId) {
        try{
        RetrofitManager.getDefault().create(ProductApi.class).cartShow(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Cart>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<Cart> carts) {
                        if(productShowPresenterLisener!=null){
                            productShowPresenterLisener.onCartSuccess(carts);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(productShowPresenterLisener!=null){
                            productShowPresenterLisener.onCartShowFailed(e.getMessage().toString());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
        }catch (Exception e){
            e.printStackTrace();
            if(productShowPresenterLisener!=null){
                productShowPresenterLisener.onCartShowFailed(e.getMessage().toString());
            }
        }


    }


}
