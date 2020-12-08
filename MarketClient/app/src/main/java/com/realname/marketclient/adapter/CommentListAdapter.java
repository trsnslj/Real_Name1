package com.realname.marketclient.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.marketclient.R;
import com.realname.marketclient.bean.Comment;
import com.realname.marketclient.net.HttpUtil;

import java.util.List;

public class CommentListAdapter extends BaseAdapter {
    private List<Comment> comments;
    private Context context;

    public CommentListAdapter(List<Comment> comments, Context context) {
        this.comments = comments;
        this.context = context;
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Object getItem(int position) {
        return comments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    static class ViewHolder{
        ImageView userHead;
        TextView userName;
        TextView userComment;
     //   TextView userCommentDate;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.comment_list_item_layout,null);
            holder=new ViewHolder();
            init(holder,convertView);
            convertView.setTag(holder);
        }else{
            holder=(ViewHolder)convertView.getTag();
        }
        holder.userName.setText(comments.get(position).getUserName());
        HttpUtil.setImageResource(context,holder.userHead,comments.get(position).getUserImg());
        holder.userComment.setText(comments.get(position).getCommentContent());
   //     holder.userComment.setText(DateConvert.changeDate(comments.get(position).getCommentDate()) );
        return convertView;
    }

    public void init(ViewHolder holder,View view){
        holder.userHead=(ImageView)view.findViewById(R.id.user_head);
        holder.userName=(TextView) view.findViewById(R.id.user_name);
        holder.userComment=(TextView) view.findViewById(R.id.user_comment);
     //   holder.userCommentDate=(TextView) view.findViewById(R.id.user_commentdate);
    }
}
