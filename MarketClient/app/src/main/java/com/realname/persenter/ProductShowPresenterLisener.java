package com.realname.persenter;

import com.realname.marketclient.bean.Cart;
import com.realname.marketclient.bean.Product;

import java.util.List;

public interface ProductShowPresenterLisener {
    void onProductShowSuccess(List<Product> productShowBean);
    void onProductShowFailed(String error);

    void onCartSuccess(List<Cart> productShowBean);
    void onCartShowFailed(String error);
}
