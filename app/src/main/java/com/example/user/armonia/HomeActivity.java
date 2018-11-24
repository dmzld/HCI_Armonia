package com.example.user.armonia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        // 버튼 누른 결과를 보여주기 위해 TextView를 사용합니다.

        final TextView message = (TextView)findViewById(R.id.textview_main_message);


        // 버튼 클릭시 사용되는 리스너를 구현합니다.

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigationView_main_menu);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                        // 어떤 메뉴 아이템이 터치되었는지 확인합니다.
                        switch (item.getItemId()) {

                            case R.id.menuitem_bottombar_myClub:

                                message.setText("내동아리 버튼을 눌렀습니다.");

                                return true;

                            case R.id.menuitem_bottombar_search:

                                message.setText("검색 버튼을 눌렀습니다.");

                                return true;

                            case R.id.menuitem_bottombar_home:

                                message.setText("홈 버튼을 눌렀습니다.");

                                return true;

                            case R.id.menuitem_bottombar_collaboBoard:

                                message.setText("연합게시판 버튼을 눌렀습니다.");

                                return true;

                            case R.id.menuitem_bottombar_add:

                                message.setText("기타 버튼을 눌렀습니다.");

                                return true;
                        }
                        return false;
                    }
                });

    }
}