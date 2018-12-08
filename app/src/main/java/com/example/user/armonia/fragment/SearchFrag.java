package com.example.user.armonia.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.ListView;

import android.widget.SearchView;

import com.example.user.armonia.list.ListClub;
import com.example.user.armonia.R;
import com.example.user.armonia.adapter.AdapterListClub;

import java.util.ArrayList;


public class SearchFrag extends Fragment {

    SearchView searchClub;
    public static SearchFrag newInstance() { return new SearchFrag(); }
    ListView listClubView;
    AdapterListClub adpaterListClub;
    ArrayList<ListClub> listClubArrayList;


    Button btn;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        listClubView = (ListView)view.findViewById(R.id.list_club);
        listClubArrayList = new ArrayList<ListClub>();

        //나중에 db에서 받아와서 add시켜야함 테스트용
        listClubArrayList.add(new ListClub(R.mipmap.ic_launcher,"볼랜드","체육"));
        listClubArrayList.add(new ListClub(R.mipmap.ic_launcher,"한터","체육"));
        listClubArrayList.add(new ListClub(R.mipmap.ic_launcher,"디스","체육"));
        listClubArrayList.add(new ListClub(R.mipmap.ic_launcher,"몰라","체육"));

        adpaterListClub = new AdapterListClub(getActivity(),listClubArrayList);
        listClubView.setAdapter(adpaterListClub);
        return view;

    }



}
