package com.realname.marketclient.activity;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public abstract class BaseFragment extends Fragment {


    private boolean isViewVisibity=false;
    private boolean isViewCreated=false;
    public Context mContext;
    private Toast mToast;
    private ProgressDialog dialog;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(getArguments());
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        initListener();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(mContext).inflate(getLayoutId(), container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        isViewCreated = true;
        initView();
        lasyLoadData();
    }

    /**
     * 适合viewpager+fragment
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isViewVisibity = true;
            lasyLoadData();
        } else {
            isViewVisibity = false;
            viewInvisible();
        }
    }


    private void lasyLoadData(){
        if (isViewCreated && isViewVisibity) {
            doLazy();
            isViewCreated = false;
            isViewVisibity = false;
        }
    }

    /**
     * 适合fragment的show和hide
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {
            hide();
        } else {
            show();
        }
    }

    abstract public int getLayoutId();

    public void init(Bundle arguments){}

    protected void   initView() {}

    protected void initData() {}

    protected void initListener() {}

    protected void  doLazy() {}

    protected void  viewInvisible() {}

    protected void  hide() {}

    protected void  show() {}

    protected void toast(String s){
        if(mToast==null){
            mToast=Toast.makeText(mContext,s,Toast.LENGTH_LONG);
        }else {
            mToast.setText(s);
        }
        mToast.show();

    }

    public void showLoading(int i){
        if(dialog==null){
            dialog =new ProgressDialog(mContext);
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setMax(i);
        }
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
        }

    }


    public void addProgress(int i) {
        dialog.setProgress(i);
    }

    public void finishLoading() {
        dialog.dismiss();
    }





}
