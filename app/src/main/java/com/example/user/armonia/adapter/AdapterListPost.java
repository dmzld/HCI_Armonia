package com.example.user.armonia.adapter;

import android.content.Context;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.user.armonia.list.ListPost;
import com.example.user.armonia.R;

import java.util.ArrayList;

public class AdapterListPost extends BaseAdapter {

    Context context;
    ArrayList<ListPost> listPostArrayList;

    class ViewHolder{
        TextView post_Title;
        TextView post_Name;
        TextView post_Date;
    }

    ViewHolder viewHolder;

    public AdapterListPost(Context context, ArrayList<ListPost> listPostArrayList) {
        this.context = context;
        this.listPostArrayList = listPostArrayList;
    }

    @Override
    public int getCount() {
        return this.listPostArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listPostArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_post,null);

            viewHolder = new ViewHolder();

            viewHolder.post_Date = (TextView)convertView.findViewById(R.id.post_Date);
            viewHolder.post_Name = (TextView)convertView.findViewById(R.id.post_Name);
            viewHolder.post_Title= (TextView)convertView.findViewById(R.id.post_Title);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }


        viewHolder.post_Date.setText(listPostArrayList.get(position).getPost_Date().toString());
        viewHolder.post_Name.setText(listPostArrayList.get(position).getPost_Name());
        viewHolder.post_Title.setText(listPostArrayList.get(position).getPost_Title());

        return convertView;
    }
}
