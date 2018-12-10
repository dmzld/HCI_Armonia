package com.example.user.armonia.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.example.user.armonia.R;
import com.example.user.armonia.fragment.ClubAlbumFrag;
import com.example.user.armonia.fragment.ClubBoardFrag;
import com.example.user.armonia.fragment.ClubCalendarFrag;
import com.example.user.armonia.fragment.ClubHomeFrag;
import com.example.user.armonia.fragment.ClubNotiBoardFrag;


public class ClubPageActivity extends AppCompatActivity {
//ClubHomeFrag, NotiBoardFrag, ClubCalendarFrag, ClubAlbumFrag, ClubBoardFrag를 갖는다

    private String email;
    private String user;
    private String clubName;
    private String clubCategory;



    private Fragment fragmentClub;
    private FragmentManager fragmentClubManager;
    //private Button btnLogout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_page);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        user = intent.getStringExtra("user");
        clubName = intent.getStringExtra("clubName");
        clubCategory = intent.getStringExtra("clubCategory");

        fragmentClubManager = getSupportFragmentManager();

        //하단에 버튼네비게이션 생성
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomNavigationView_club_menu);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //첫 화면에 home fragment 만들기
        fragmentClubManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentClubManager.beginTransaction();
        transaction.add(R.id.club_fragment_container, ClubHomeFrag.newInstance(email,user,clubName,clubCategory)).commit();

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menuitem_bottombar_home:
                    replaceFragment(ClubHomeFrag.newInstance(email,user,clubName,clubCategory));
                    return true;

                case R.id.menuitem_bottombar_notification_board:
                    replaceFragment(ClubNotiBoardFrag.newInstance(email,user,clubName,clubCategory));
                    return true;

                case R.id.menuitem_bottombar_calendar:
                    replaceFragment(ClubCalendarFrag.newInstance());
                    return true;

                case R.id.menuitem_bottombar_album:
                    replaceFragment(ClubAlbumFrag.newInstance());
                    return true;

                case R.id.menuitem_bottombar_board:
                    replaceFragment(ClubBoardFrag.newInstance(email,user,clubName,clubCategory));
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
