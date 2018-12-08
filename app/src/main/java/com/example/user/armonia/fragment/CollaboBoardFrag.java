package com.example.user.armonia.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.user.armonia.R;
import com.example.user.armonia.adapter.AdapterListPost;
import com.example.user.armonia.list.ListPost;

import java.util.ArrayList;


public class CollaboBoardFrag extends Fragment {

    Button btnWrite;
    ListView listCollaView;
    AdapterListPost adapterListColla;
    ArrayList<ListPost> collaArrayList;

    public static CollaboBoardFrag newInstance() { return new CollaboBoardFrag(); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_collabo_board, container, false);

        listCollaView = (ListView)view.findViewById(R.id.listPostView);
        collaArrayList = new ArrayList<ListPost>();

        // 아직 DB 연동 안함

        collaArrayList.add(new ListPost("1번째 글","이주형","2018/12/05"));
        collaArrayList.add(new ListPost("2번째 글","이주형","2018/12/05"));
        collaArrayList.add(new ListPost("3번째 글","이주형","2018/12/05"));
        collaArrayList.add(new ListPost("4번째 글","이주형","2018/12/05"));
        collaArrayList.add(new ListPost("5번째 글","이주형","2018/12/05"));
        collaArrayList.add(new ListPost("6번째 글","이주형","2018/12/05"));
        collaArrayList.add(new ListPost("7번째 글","이주형","2018/12/05"));
        collaArrayList.add(new ListPost("8번째 글","이주형","2018/12/05"));



        adapterListColla = new AdapterListPost(getActivity(),collaArrayList);
        listCollaView.setAdapter(adapterListColla);
        return view;
    }
}