package com.example.user.armonia.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.user.armonia.R;
import com.example.user.armonia.adapter.AdapterListPost;
import com.example.user.armonia.list.ListPost;

import java.util.ArrayList;

public class FreeBoardActivity extends AppCompatActivity {

    ListView listFreeView;
    AdapterListPost adapterListFree;
    ArrayList<ListPost> freeArrayList;
    Button btnWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_board);

        //전체 자유게시판
        listFreeView = (ListView)findViewById(R.id.listFreeView);
        freeArrayList = new ArrayList<ListPost>();
        btnWrite = (Button)findViewById(R.id.btnWrite);

        // 아직 DB 연동 안함
        freeArrayList.add(new ListPost("1번째 글","이주형","2018/12/05"));
        freeArrayList.add(new ListPost("2번째 글","이주형","2018/12/05"));
        freeArrayList.add(new ListPost("3번째 글","이주형","2018/12/05"));
        freeArrayList.add(new ListPost("4번째 글","이주형","2018/12/05"));
        freeArrayList.add(new ListPost("5번째 글","이주형","2018/12/05"));

        adapterListFree = new AdapterListPost(getApplicationContext(),freeArrayList);
        listFreeView.setAdapter(adapterListFree);

        listFreeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(), PostActivity.class);
                //intent.putExtra();
                startActivity(intent);
            }
        });
    }
}
