package com.example.user.armonia.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.armonia.R;

public class WritePostActivity extends AppCompatActivity {

    String email;
    String user;

    Button btnSuccess;
    Button btnCancel;
    EditText writeTitle;
    EditText writeContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post);

        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        user = intent.getStringExtra("user");

        btnSuccess = (Button)findViewById(R.id.btnSuccess);
        btnSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 자신을 호출한 Activity로 데이터를 보낸다.
                //setResult(RESULT_OK, result);
                finish();
            }
        });

        btnCancel = (Button)findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //글쓰기 php

    }
}
