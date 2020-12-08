package com.realname.marketclient.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.school.marketclient.R;
import com.realname.marketclient.activity.MainActivity;
import com.realname.marketclient.activity.MineActivity;
import com.realname.marketclient.activity.OrderActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class bottom_fragment extends Fragment implements View.OnClickListener {
    private LinearLayout index,order,mime;
    private ImageView bottom_index_img;
    final int[] colors = new int[]{R.color.black,R.color.red};

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();

    }

    private TextView index_text,order_text,mime_text;

    public bottom_fragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.bottomfragment, container, false);
    }

    private void init() {

        index=  getActivity().findViewById(R.id.index);
        order=getActivity(). findViewById(R.id.order);
        mime=getActivity().findViewById(R.id.mime);
        index_text=getActivity().findViewById(R.id.index_text);
        order_text=getActivity().findViewById(R.id.order_text);
        mime_text=getActivity().findViewById(R.id.mine_text);
        bottom_index_img=getActivity().findViewById(R.id.bottom_index_img);

        index.setOnClickListener(this);
        order.setOnClickListener(this);
        mime.setOnClickListener(this);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }

    @SuppressLint("ResourceAsColor")
    public void onClick(View view) {
        switch (view.getId())
        {
            //首页
            case R.id.index:
                Intent intent=new Intent(getActivity(), MainActivity.class);
                getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                startActivity(intent);
                break;
            //订单
            case R.id.order:
                order_text.setTextColor(R.color.red);
                //跳转到order
                Intent intent1=new Intent(getActivity(), OrderActivity.class);
                getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                startActivity(intent1);
                break;
            //我的
            case R.id.mime:
                mime_text.setTextColor(R.color.red);
                Intent intent2=new Intent(getActivity(), MineActivity.class);
                getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                startActivity(intent2);
                break;
            default:
                break;
            //发货，确认，评论，
        }

    }
}
