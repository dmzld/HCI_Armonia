package com.example.user.armonia.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.armonia.list.ListClub;
import com.example.user.armonia.R;

import java.util.ArrayList;

public class AdapterListClub extends BaseAdapter {

    Context context;
    ArrayList<ListClub> listCLubArrayList;

    TextView text_clubCategory;
    TextView text_clubName;
    ImageView image_club;

    public AdapterListClub(Context context, ArrayList<ListClub> listCLubArrayList) {
        this.context = context;
        this.listCLubArrayList = listCLubArrayList;
    }

    @Override
    public int getCount() {
        return this.listCLubArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.listCLubArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_club,null);

            text_clubName=(TextView)convertView.findViewById(R.id.club_name);
            text_clubCategory=(TextView)convertView.findViewById(R.id.club_category);
            image_club=(ImageView)convertView.findViewById(R.id.image_club);

            text_clubName.setText(listCLubArrayList.get(position).getClub_name());
            text_clubCategory.setText(listCLubArrayList.get(position).getClub_category());
            image_club.setImageResource(listCLubArrayList.get(position).getImage_club());
        }
        return convertView;
    }
}
