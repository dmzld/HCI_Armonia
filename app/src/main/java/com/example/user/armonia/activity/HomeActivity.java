package com.example.user.armonia.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.user.armonia.R;
import com.example.user.armonia.fragment.AddFrag;
import com.example.user.armonia.fragment.CollaboBoardFrag;
import com.example.user.armonia.fragment.HomeFrag;
import com.example.user.armonia.fragment.MyClubFrag;
import com.example.user.armonia.fragment.SearchFrag;
import com.google.firebase.auth.FirebaseAuth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class HomeActivity extends AppCompatActivity {
    //AddFage, CollaboBoardFrag, HomeFrag, MyClubFrag, SearchFage를 갖는다
    private Fragment fragment;
    private FragmentManager fragmentManager;


    Bundle bundle = new Bundle();


    //private Button btnLogout;
    private FirebaseAuth auth;
    private String email;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = FirebaseAuth.getInstance();
      //  btnLogout = (Button)findViewById(R.id.btnLogout);
        fragmentManager = getSupportFragmentManager();


        //id 받아와서 myclub 등에서 줘야햄
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        user = intent.getStringExtra("user");




        //하단에 버튼네비게이션 생성
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomNavigationView_main_menu);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //첫 화면에 home fragment 만들기
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container, HomeFrag.newInstance(email,user)).commit();


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

                case R.id.menuitem_bottombar_home:
                    replaceFragment(HomeFrag.newInstance(email,user));
                    return true;

                case R.id.menuitem_bottombar_myClub:
                    replaceFragment(MyClubFrag.newInstance(email,user));
                    return true;

                case R.id.menuitem_bottombar_search:
                    replaceFragment(SearchFrag.newInstance(email,user));
                    return true;

                case R.id.menuitem_bottombar_collaboBoard:
                    replaceFragment(CollaboBoardFrag.newInstance(email,user));
                    return true;

                case R.id.menuitem_bottombar_add:
                    replaceFragment(AddFrag.newInstance(email,user));
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