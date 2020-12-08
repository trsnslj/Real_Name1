package com.realname.marketclient.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.school.marketclient.R;
import com.realname.marketclient.activity.ShowProductActivity;
import com.realname.marketclient.adapter.CommentListAdapter;
import com.realname.marketclient.bean.Comment;

import java.util.List;


public class ProductCommentFragment extends Fragment {
    private View view;
    private List<Comment> comments;
    private ListView commentList;
    private CommentListAdapter adapter;
    private TextView commentNum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_product_comment, container, false);
        init();
        commentList.setAdapter(adapter);
        return view;
    }

    public void init(){
        ShowProductActivity activity = (ShowProductActivity)getActivity();
        comments=activity.getComments();
        commentNum=view.findViewById(R.id.comment_num);
        commentNum.setText("用户点评("+comments.size()+")");
        adapter=new CommentListAdapter(comments,getContext());
        commentList=(ListView) view.findViewById(R.id.comment_list);
    }

}
