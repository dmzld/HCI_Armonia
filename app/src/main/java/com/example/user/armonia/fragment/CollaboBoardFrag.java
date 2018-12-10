package com.example.user.armonia.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.user.armonia.R;
import com.example.user.armonia.activity.PostActivity;
import com.example.user.armonia.activity.WritePostActivity;
import com.example.user.armonia.adapter.AdapterListPost;
import com.example.user.armonia.list.ListPost;

import java.util.ArrayList;


public class CollaboBoardFrag extends Fragment {

    static String curEmail="curEmail";
    static String curUser="curUser";
    String email;
    String user;

    public static CollaboBoardFrag newInstance(String email, String user) {
        CollaboBoardFrag collaboBoardFrag = new CollaboBoardFrag();
        Bundle args = new Bundle();
        args.putString(curEmail,email);
        args.putString(curUser,user);
        collaboBoardFrag.setArguments(args);
        return collaboBoardFrag;
    }

    Button btnWrite;
    ListView listCollaView;
    AdapterListPost adapterListColla;
    ArrayList<ListPost> collaArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_collabo_board, container, false);

        listCollaView = (ListView)view.findViewById(R.id.listCollaView);
        collaArrayList = new ArrayList<ListPost>();
        btnWrite = (Button)view.findViewById(R.id.btnWrite);
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),WritePostActivity.class);
                startActivity(intent);
            }
        });

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

        listCollaView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getActivity(), PostActivity.class);
                //지금은 일단 그냥 클럽 액티비티로
                //intent.putExtra();
                startActivity(intent);
            }
        });
        return view;
    }
}