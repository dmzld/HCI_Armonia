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

public class PostActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_activitiy);


    }
}