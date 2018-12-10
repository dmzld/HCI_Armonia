package com.example.user.armonia.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.user.armonia.R;

public class PostActivity extends AppCompatActivity {

//    String postTitle;
//    String postDate;
//    String postUser;
//    String postContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_activitiy);

        TextView postTitle = (TextView)findViewById(R.id.post_Title);
        TextView postDate = (TextView)findViewById(R.id.post_Date);
        TextView postUser = (TextView)findViewById(R.id.post_User);
        TextView postContent = (TextView)findViewById(R.id.post_Content);

        Intent intent = getIntent();

        postTitle.setText(intent.getStringExtra("Title"));
        postDate.setText(intent.getStringExtra("Date"));
        postUser.setText(intent.getStringExtra("User"));
        postContent.setText(intent.getStringExtra("Content"));

    }
}
