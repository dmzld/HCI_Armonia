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
import java.util.List;

public class ClubBoardFrag extends Fragment {

    public static ClubBoardFrag newInstance() {
        return new ClubBoardFrag();
    }
    Button btnWrite;
    ListView listPostView;
    AdapterListPost adapterListPost;
    ArrayList<ListPost> postArrayList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_club_board, container, false);

        listPostView = (ListView)view.findViewById(R.id.listPostView);
        postArrayList = new ArrayList<ListPost>();
        btnWrite = (Button)view.findViewById(R.id.btnWrite);
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),WritePostActivity.class);
                startActivity(intent);
            }
        });


        // 아직 DB 연동 안함

        postArrayList.add(new ListPost("1번째 글","이주형","2018/12/05"));
        postArrayList.add(new ListPost("2번째 글","이주형","2018/12/05"));
        postArrayList.add(new ListPost("3번째 글","이주형","2018/12/05"));
        postArrayList.add(new ListPost("4번째 글","이주형","2018/12/05"));
        postArrayList.add(new ListPost("5번째 글","이주형","2018/12/05"));
        postArrayList.add(new ListPost("6번째 글","이주형","2018/12/05"));
        postArrayList.add(new ListPost("7번째 글","이주형","2018/12/05"));
        postArrayList.add(new ListPost("8번째 글","이주형","2018/12/05"));

        adapterListPost = new AdapterListPost(getActivity(),postArrayList);
        listPostView.setAdapter(adapterListPost);

        return view;
    }

}
