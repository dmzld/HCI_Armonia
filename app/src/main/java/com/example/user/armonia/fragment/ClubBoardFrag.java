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

import com.example.user.armonia.activity.PostActivity;
import com.example.user.armonia.activity.WritePostActivity;
import com.example.user.armonia.list.ListPost;
import com.example.user.armonia.R;
import com.example.user.armonia.adapter.AdapterListPost;

import java.util.ArrayList;

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

        listPostView = (ListView)view.findViewById(R.id.listClubBoardView);
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

        listPostView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
