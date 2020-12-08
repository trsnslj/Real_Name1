package com.realname.marketclient.fragment;

import android.app.ProgressDialog;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import com.school.marketclient.R;
import com.realname.marketclient.activity.BaseFragment;
import com.realname.marketclient.adapter.ProductListAdapter;
import com.realname.marketclient.bean.Cart;
import com.realname.marketclient.bean.Product;
import com.realname.persenter.ProductShowPresenterImpl;
import com.realname.view.ProductShowViewLisener;

import java.util.List;

public class HomeFragment extends BaseFragment implements View.OnClickListener{

    public GridView mListView;
    private ProductListAdapter adapter;
    ProgressDialog progressBar;
    @Override
    public void onClick(View v) {

    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e("zpx","hidde>>>>>>>>>>>>>.."+hidden);
        if(!hidden){
            initView();
            initData();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        super.initView();
        progressBar= ProgressDialog.show(getActivity(),"请稍后...","获取网络资源中...");
        mListView=(GridView)getView().findViewById(R.id.productList);
        mListView.setNumColumns(2);
        adapter=new ProductListAdapter(getActivity());
        mListView.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        super.initData();
        ProductShowPresenterImpl.getInstance().setProductShowViewLisener(new ProductShowViewLisener() {
            @Override
            public void onProductShowSuccess(List<Product> productShowBean) {
                Log.e("zpx","===返回成功==="+productShowBean);
                adapter.setData(productShowBean);
                progressBar.dismiss();
            }

            @Override
            public void onProductShowFailed(String error) {
                toast("网络加载失败"+error);
                progressBar.dismiss();
                Log.e("zpx","===查询失败==="+error);
            }

            @Override
            public void onCartSuccess(List<Cart> productShowBean) {

            }

            @Override
            public void onCartFailed(String error) {

            }
        });
        ProductShowPresenterImpl.getInstance().productShow();

    }
}
