package com.example.user.armonia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ClubPageActivity extends AppCompatActivity {

    ListView listClubPageView;
    AdapterClubPage adpaterClubPage;
    ArrayList<ListClubPage> listClubPageArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_page);

        listClubPageView = (ListView)findViewById(R.id.clubPageList);
        listClubPageArrayList = new ArrayList<ListClubPage>();

        //나중에 db에서 받아와서 add시켜야함 테스트용
        //첫번째는 동아리 메인 사진, 이름, 독립적인 item
        //listClubPageArrayList.add(new ListClub(R.mipmap.ic_launcher,"볼랜드","체육"));

        //그 이후는 동아리 페이지에 올린 글
        listClubPageArrayList.add(new ListClubPage(R.mipmap.ic_launcher,"1","이윤석","2018-12-03","바이"));
        listClubPageArrayList.add(new ListClubPage(R.mipmap.ic_launcher,"2","이윤석","2018-12-03","바이"));
        listClubPageArrayList.add(new ListClubPage(R.mipmap.ic_launcher,"3","이윤석","2018-12-03","바이"));
        listClubPageArrayList.add(new ListClubPage(R.mipmap.ic_launcher,"3","이윤석","2018-12-03","바이"));
        listClubPageArrayList.add(new ListClubPage(R.mipmap.ic_launcher,"4","이윤석","2018-12-03","바이"));

        adpaterClubPage = new AdapterClubPage(ClubPageActivity.this,listClubPageArrayList);
        listClubPageView.setAdapter(adpaterClubPage);


    }
}
