package com.realname.persenter;

import com.realname.marketclient.bean.Cart;
import com.realname.marketclient.bean.Product;
import com.realname.model.model.ProductShowModelImpl;
import com.realname.view.ProductShowViewLisener;

import java.util.List;

public class ProductShowPresenterImpl implements ProductShowPresenterLisener {

    public static ProductShowPresenterImpl INSTANCE;
    public static synchronized ProductShowPresenterImpl getInstance(){
        if(INSTANCE==null){
           INSTANCE=new ProductShowPresenterImpl();
        }
        return INSTANCE;
    }


    private ProductShowViewLisener mProductShowViewLisener;

    public void setProductShowViewLisener(ProductShowViewLisener lisener){
        this.mProductShowViewLisener=lisener;
    }

    public void productShow(){
        ProductShowModelImpl.getInstance().setProductShowPresenterLisener(this);
        ProductShowModelImpl.getInstance().productShow();
    }
    public void cartShow(int userId){
        ProductShowModelImpl.getInstance().setProductShowPresenterLisener(this);
        ProductShowModelImpl.getInstance().cartShow(userId);
    }



    @Override
    public void onProductShowSuccess(List<Product> productShowBean) {
        if(mProductShowViewLisener!=null){
            mProductShowViewLisener.onProductShowSuccess(productShowBean);
        }
    }

    @Override
    public void onProductShowFailed(String error) {
        if(mProductShowViewLisener!=null){
            mProductShowViewLisener.onProductShowFailed(error);
        }
    }

    @Override
    public void onCartSuccess(List<Cart> productShowBean) {
        if(mProductShowViewLisener!=null){
            mProductShowViewLisener.onCartSuccess(productShowBean);
        }
    }

    @Override
    public void onCartShowFailed(String error) {
        if(mProductShowViewLisener!=null){
            mProductShowViewLisener.onCartFailed(error);
        }
    }
}
