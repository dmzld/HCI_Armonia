package com.example.user.armonia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MyClubFrag extends Fragment {

    public static MyClubFrag newInstance() {
        return new MyClubFrag();
    }
    ListView listClubView;
    AdapterListClub adpaterListClub_my;
    ArrayList<ListClub> listClubArrayList;
    Button btnsearchClub;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //RelativeLayout myClubFrag = (RelativeLayout)inflater.inflate(R.layout.frag_myclub, container, false);
        View view = inflater.inflate(R.layout.frag_myclub, container, false);

        listClubView = (ListView)view.findViewById(R.id.list_myClub);
        listClubArrayList = new ArrayList<ListClub>();

        //나중에 db에서 받아와서 add시켜야함 테스트용
        listClubArrayList.add(new ListClub(R.mipmap.ic_launcher,"볼랜드","체육"));
        listClubArrayList.add(new ListClub(R.mipmap.ic_launcher,"한터","체육"));
        listClubArrayList.add(new ListClub(R.mipmap.ic_launcher,"디스","체육"));
        listClubArrayList.add(new ListClub(R.mipmap.ic_launcher,"몰라","체육"));

        adpaterListClub_my = new AdapterListClub(getActivity(),listClubArrayList);
        listClubView.setAdapter(adpaterListClub_my);

        btnsearchClub = (Button)view.findViewById(R.id.btnSearchClub);

        listClubView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ClubPageActivity.class);
                //db로 클릭한 동아리 정보를 같이 건네서 해당 동아리 페이지로 가야함
                //지금은 일단 그냥 클럽 액티비티로
                startActivityForResult(intent,1000);
            }
        });

        btnsearchClub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //버튼 눌렀을때 searchclub frag로 갈껀데 굳이 해야됨? 밑에 바로 있음
            }
        });

        return view; // 여기서 UI를 생성해서 View를 return
    }



}