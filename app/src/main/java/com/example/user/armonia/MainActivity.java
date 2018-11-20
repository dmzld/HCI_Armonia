package com.example.user.armonia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button btnRegist;
    private Button btnLogin;
    private EditText id;
    private EditText password;


    //hihello22

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id = (EditText) findViewById(R.id.id);
        password = (EditText) findViewById(R.id.password);

        btnRegist = (Button) findViewById(R.id.btnRegist);
        btnRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegistActivity.class);
                startActivityForResult(intent,1000);
            }
        });

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if( id.getText().toString().length() == 0 ) {
                    Toast.makeText(MainActivity.this, "ID을 입력하세요!", Toast.LENGTH_SHORT).show();
                    id.requestFocus();
                    return;
                }

                // 비밀번호 입력 확인
                if( password.getText().toString().length() == 0 ) {
                    Toast.makeText(MainActivity.this, "비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    password.requestFocus();
                    return;
                }

                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivityForResult(intent,1000);
            }
        });
    }//on create
}
