package com.example.user.armonia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.armonia.list.ListClubPage;
import com.example.user.armonia.R;

import java.util.ArrayList;

public class AdapterClubPage extends BaseAdapter {

    Context context;
    ArrayList<ListClubPage> listClubPageArrayList;

    TextView text_title;
    TextView text_writer;
    TextView text_contents;
    TextView text_date;
    ImageView image;

    public AdapterClubPage(Context context, ArrayList<ListClubPage> listClubPageArrayLis) {
        this.context = context;
        this.listClubPageArrayList = listClubPageArrayList;
    }


    @Override
    public int getCount() {
        return this.listClubPageArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listClubPageArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_club_page,null);

            text_title=(TextView)convertView.findViewById(R.id.title);
            text_writer=(TextView)convertView.findViewById(R.id.writer);
            text_contents=(TextView)convertView.findViewById(R.id.contents);
            text_date=(TextView)convertView.findViewById(R.id.date);
            image=(ImageView)convertView.findViewById(R.id.image_club);

            text_title.setText(listClubPageArrayList.get(position).getTitle());
            text_writer.setText(listClubPageArrayList.get(position).getWriter());
            text_contents.setText(listClubPageArrayList.get(position).getContents());
            text_date.setText(listClubPageArrayList.get(position).getDate());
            image.setImageResource(listClubPageArrayList.get(position).getImage());
        }
        return convertView;
    }
}
