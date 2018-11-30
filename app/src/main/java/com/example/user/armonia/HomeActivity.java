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

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    private Fragment fragment;
    private FragmentManager fragmentManager;
    //private Button btnLogout;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = FirebaseAuth.getInstance();
      //  btnLogout = (Button)findViewById(R.id.btnLogout);

        fragmentManager = getSupportFragmentManager();


        //하단에 버튼네비게이션 생성
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomNavigationView_main_menu);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //첫 화면에 home fragment 만들기
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container, HomeFrag.newInstance()).commit();


        /*btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                LoginManager.getInstance().logOut();
                finish();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);

            }
        });*/
    }

    //버튼을 눌렀을때 fragment가 바뀌기 위한 장치
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menuitem_bottombar_myClub:
                    replaceFragment(MyClubFrag.newInstance());
                    return true;

                case R.id.menuitem_bottombar_search:
                    replaceFragment(SearchFrag.newInstance());
                    return true;

                case R.id.menuitem_bottombar_home:
                    replaceFragment(HomeFrag.newInstance());
                    return true;

                case R.id.menuitem_bottombar_collaboBoard:
                    replaceFragment(CollaboBoardFrag.newInstance());
                    return true;

                case R.id.menuitem_bottombar_add:
                    replaceFragment(AddFrag.newInstance());
                    return true;
            }
            return false;
        }

        //다른 버튼을 눌렀을때, fragment를 바꾸기 위한 함수
        private void replaceFragment(Fragment fragment) {
            fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment).commit();
        }
    };
}