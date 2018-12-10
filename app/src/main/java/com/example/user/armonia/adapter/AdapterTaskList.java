package com.example.user.armonia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.user.armonia.R;
import com.example.user.armonia.list.ListTask;

import java.util.ArrayList;

public class AdapterTaskList extends BaseAdapter {
    Context context;
    ArrayList<ListTask> listTaskArrayList;

    TextView task_title;
    TextView task_content;

    public AdapterTaskList(Context context, ArrayList<ListTask> listTaskArrayList) {
        this.context = context;
        this.listTaskArrayList = listTaskArrayList;
    }

    @Override
    public int getCount() {
        return this.listTaskArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listTaskArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_club,null);

            task_title=(TextView)convertView.findViewById(R.id.task_title);
            task_content=(TextView)convertView.findViewById(R.id.task_content);

            task_title.setText(listTaskArrayList.get(position).getTask_Title());
            task_content.setText(listTaskArrayList.get(position).getTask_Content());
        }
        return convertView;
    }
}
