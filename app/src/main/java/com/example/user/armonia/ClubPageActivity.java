package com.example.user.armonia;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ClubPageActivity extends AppCompatActivity {
//ClubHomeFrag, NotiBoardFrag, ClubCalendarFrag, ClubAlbumFrag, ClubBoardFrag를 갖는다

    private Fragment fragmentClub;
    private FragmentManager fragmentClubManager;
    //private Button btnLogout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_page);


        fragmentClubManager = getSupportFragmentManager();

        //하단에 버튼네비게이션 생성
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomNavigationView_club_menu);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //첫 화면에 home fragment 만들기
        fragmentClubManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentClubManager.beginTransaction();
        transaction.add(R.id.club_fragment_container, ClubHomeFrag.newInstance()).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menuitem_bottombar_home:
                    replaceFragment(ClubHomeFrag.newInstance());
                    return true;

                case R.id.menuitem_bottombar_notification_board:
                    replaceFragment(ClubNotiBoardFrag.newInstance());
                    return true;

                case R.id.menuitem_bottombar_calendar:
                    replaceFragment(ClubCalendarFrag.newInstance());
                    return true;

                case R.id.menuitem_bottombar_album:
                    replaceFragment(ClubAlbumFrag.newInstance());
                    return true;

                case R.id.menuitem_bottombar_board:
                    replaceFragment(ClubBoardFrag.newInstance());
                    return true;

            }
            return false;
        }

        //다른 버튼을 눌렀을때, fragment를 바꾸기 위한 함수
        private void replaceFragment(Fragment fragment) {
            fragmentClubManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentClubManager.beginTransaction();
            fragmentTransaction.replace(R.id.club_fragment_container, fragment).commit();
        }
    };
}

/*ListView listClubPageView;
    AdapterClubPage adpaterClubPage;
    ArrayList<ListClubPage> listClubPageArrayList;

* listClubPageView = (ListView)findViewById(R.id.clubPageList);
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
*
*
* */
