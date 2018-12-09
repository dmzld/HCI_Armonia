package com.example.user.armonia;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyInfoChangePassword extends AppCompatActivity {

    private TextView detail;
    private TextInputLayout currentpwd;
    private TextInputLayout changepwd;
    private Button setpwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info_change_password);

        this.detail = (TextView)findViewById(R.id.detail);
        detail.setText("현재 설정하신 비밀번호와"+ "<br />"+ "바꾸실 비밀번호를 설정해 주세요"+ "<br />"+ "최대 13글자입니다.");
        this.currentpwd = (TextInputLayout)findViewById(R.id.currentpwd);
        this.changepwd = (TextInputLayout)findViewById(R.id.changepwd);
        this.setpwd = (Button)findViewById(R.id.setpwd);

        currentpwd.setCounterEnabled(true);
        currentpwd.setCounterMaxLength(13);
        changepwd.setCounterEnabled(true);
        changepwd.setCounterMaxLength(13);

        setpwd.setOnClickListener(new onClickedSetPwd());

    }
    class onClickedSetPwd implements OnClickListener {
        @Override
        public void onClick(View v){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MyInfoChangePassword.this);
            alertDialogBuilder.setTitle("비밀번호 변경");
            alertDialogBuilder.setMessage("이대로 변경하시겠습니까?");
            alertDialogBuilder.setPositiveButton("예",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(),"이전화면으로 돌아갑니다",Toast.LENGTH_LONG).show();
                            finish();
                        }
                    });
            alertDialogBuilder.setNegativeButton("아니오",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(getApplicationContext(),"다시 입력해 주세요",Toast.LENGTH_LONG).show();
                        }
                    });
            alertDialogBuilder.show();
        }
    }
}
