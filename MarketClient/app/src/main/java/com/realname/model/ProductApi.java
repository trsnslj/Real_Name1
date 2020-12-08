package com.realname.model;

import com.realname.marketclient.bean.Cart;
import com.realname.marketclient.bean.Product;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ProductApi {
    @GET("product/show")
    Observable<List<Product>> productShow();
    @GET("user/getCarts")
    Observable<List<Cart>> cartShow(@Query("userId") int userId);
//    @POST("login")
//    Observable<ProductShowBean> productShow(@Body ProductShowEntity body);


}
