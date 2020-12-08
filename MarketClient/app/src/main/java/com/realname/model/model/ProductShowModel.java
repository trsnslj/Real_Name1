package com.realname.model.model;

import com.realname.marketclient.bean.Cart;
import com.realname.marketclient.bean.Product;
import com.realname.utils.RetrofitManager;
import com.realname.model.ProductApi;

import java.util.List;

import io.reactivex.Observable;

public class ProductShowModel {

    public Observable<List<Product>> productShow(){
        return RetrofitManager.getDefault().create(ProductApi.class).productShow();
    }

    public Observable<List<Cart>> cartShow(int userId){
        return RetrofitManager.getDefault().create(ProductApi.class).cartShow(userId);
    }











}
