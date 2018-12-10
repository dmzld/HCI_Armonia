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
import android.widget.TextView;

import com.example.user.armonia.R;
import com.example.user.armonia.activity.PostActivity;
import com.example.user.armonia.activity.WritePostActivity;
import com.example.user.armonia.adapter.AdapterListPost;
import com.example.user.armonia.list.ListPost;

import java.util.ArrayList;

public class ClubNotiBoardFrag extends Fragment {

    public static ClubNotiBoardFrag newInstance() {
        return new ClubNotiBoardFrag();
    }
    Button btnWrite;
    ListView listNotiView;
    AdapterListPost adapterListNoti;
    ArrayList<ListPost> notiArrayList;
    TextView textNoti;

    static String curEmail="curEmail";
    static String curUser="curUser";
    static String curClubName="curClubName";
    static String curCategory="curCategory";
    String email;
    String user;
    String clubName;
    String category;

    public static ClubNotiBoardFrag newInstance(String email, String user, String clubName, String category) {
        ClubNotiBoardFrag clubNotiBoardFrag = new ClubNotiBoardFrag();
        Bundle args = new Bundle();
        args.putString(curEmail,email);
        args.putString(curUser,user);
        args.putString(curClubName,clubName);
        args.putString(curCategory,category);
        clubNotiBoardFrag.setArguments(args);
        return clubNotiBoardFrag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_club_noti_board, container, false);


        if(getArguments()!=null){
            email=getArguments().getString(curEmail);
            user=getArguments().getString(curUser);
            clubName=getArguments().getString(curClubName);
            category=getArguments().getString(curCategory);
        }


        listNotiView = (ListView)view.findViewById(R.id.listNotiView);
        notiArrayList = new ArrayList<ListPost>();

        textNoti = (TextView)view.findViewById(R.id.textNoti);
        textNoti.setText(clubName+" 공지사항");
        btnWrite = (Button)view.findViewById(R.id.btnWrite);
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),WritePostActivity.class);
                startActivity(intent);
            }
        });

        // 아직 DB 연동 안함

        notiArrayList.add(new ListPost("1번째 글","이주형","2018/12/05"));
        notiArrayList.add(new ListPost("2번째 글","이주형","2018/12/05"));
        notiArrayList.add(new ListPost("3번째 글","이주형","2018/12/05"));
        notiArrayList.add(new ListPost("4번째 글","이주형","2018/12/05"));
        notiArrayList.add(new ListPost("5번째 글","이주형","2018/12/05"));
        notiArrayList.add(new ListPost("6번째 글","이주형","2018/12/05"));
        notiArrayList.add(new ListPost("7번째 글","이주형","2018/12/05"));
        notiArrayList.add(new ListPost("8번째 글","이주형","2018/12/05"));
        notiArrayList.add(new ListPost("9번째 글","이주형","2018/12/05"));
        notiArrayList.add(new ListPost("10번째 글","이주형","2018/12/05"));



        adapterListNoti = new AdapterListPost(getActivity(),notiArrayList);
        listNotiView.setAdapter(adapterListNoti);

        listNotiView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
