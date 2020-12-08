package com.realname.view;

import com.realname.marketclient.bean.Cart;
import com.realname.marketclient.bean.Product;

import java.util.List;

public interface ProductShowViewLisener {
    void onProductShowSuccess(List<Product> productShowBean);
    void onProductShowFailed(String error);
    void onCartSuccess(List<Cart> productShowBean);
    void onCartFailed(String error);

}
