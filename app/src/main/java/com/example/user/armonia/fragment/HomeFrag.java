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
import com.example.user.armonia.activity.FreeBoardActivity;
import com.example.user.armonia.activity.PostActivity;
import com.example.user.armonia.adapter.AdapterListPost;
import com.example.user.armonia.list.ListPost;

import java.util.ArrayList;

public class HomeFrag extends Fragment {

    static String curEmail="curEmail";
    static String curUser="curUser";
    String email;
    String user;

    public static HomeFrag newInstance(String email,String user) {
        HomeFrag homeFrag = new HomeFrag();
        Bundle args = new Bundle();
        args.putString(curEmail,email);
        args.putString(curUser,user);
        homeFrag.setArguments(args);
        return homeFrag;
    }


    TextView textCurUser;
    //동아리게시판
    ListView listHomeClubView;
    AdapterListPost adapterListHomeClub;
    ArrayList<ListPost> homeClubArrayList;

    //전체자유게시판
    ListView listFreeView;
    AdapterListPost adapterListFree;
    ArrayList<ListPost> freeArrayList;
    Button btnMoreFree;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.frag_home, container, false);

        if(getArguments()!=null){
            email=getArguments().getString(curEmail);
            user=getArguments().getString(curUser);

        }
        textCurUser = (TextView)view.findViewById(R.id.textCurUser);
        textCurUser.setText(user+"님");


        //여기서는 item 각각 5개씩만 불러올 것


        //동아리 게시판
        listHomeClubView = (ListView)view.findViewById(R.id.listHomeClubView);
        homeClubArrayList = new ArrayList<ListPost>();


        // 아직 DB 연동 안함
        homeClubArrayList.add(new ListPost("1번째 홈클럽글","이주형","2018/12/05"));
        homeClubArrayList.add(new ListPost("1번째 글","이주형","2018/12/05"));
        homeClubArrayList.add(new ListPost("1번째 글","이주형","2018/12/05"));
        homeClubArrayList.add(new ListPost("1번째 글","이주형","2018/12/05"));
        homeClubArrayList.add(new ListPost("1번째 글","이주형","2018/12/05"));

        adapterListHomeClub = new AdapterListPost(getActivity(),homeClubArrayList);
        listHomeClubView.setAdapter(adapterListHomeClub);


        listHomeClubView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getActivity(), PostActivity.class);
                //지금은 일단 그냥 클럽 액티비티로
                //intent.putExtra();
                startActivity(intent);
            }
        });





        //전체 자유게시판
        listFreeView = (ListView)view.findViewById(R.id.listHomeFreeView);
        freeArrayList = new ArrayList<ListPost>();
        btnMoreFree = (Button)view.findViewById(R.id.btnMoreFree);

        // 아직 DB 연동 안함
        freeArrayList.add(new ListPost("1번째 자유글","이주형","2018/12/05"));
        freeArrayList.add(new ListPost("2번째 글","이주형","2018/12/05"));
        freeArrayList.add(new ListPost("3번째 글","이주형","2018/12/05"));
        freeArrayList.add(new ListPost("4번째 글","이주형","2018/12/05"));
        freeArrayList.add(new ListPost("5번째 글","이주형","2018/12/05"));

        adapterListFree = new AdapterListPost(getActivity(),freeArrayList);
        listFreeView.setAdapter(adapterListFree);

        listFreeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getActivity(), PostActivity.class);
                //intent.putExtra();
                startActivity(intent);
            }
        });

        btnMoreFree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FreeBoardActivity.class);
                intent.putExtra("email",email);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });


        return view;
    }

}
