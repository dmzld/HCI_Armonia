package com.example.user.armonia.activity;


import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.armonia.MyInfoAlarmFrag;
import com.example.user.armonia.MyInfoByeByeFrag;
import com.example.user.armonia.MyInfoChangePassword;
import com.example.user.armonia.MyInfoFragMain;
import com.example.user.armonia.R;

import java.io.InputStream;

import static android.view.View.GONE;

public class MyInfoActivity extends AppCompatActivity implements MyInfoFragMain.sendToActivity{

    private Button button_modify_myinfo;
    private ImageView img_myinfo;
    private ImageView change_img;
    private ImageView change_pwd;
    private ImageView change_back;
    private TextView id_myinfo;
    private TextView name_myinfo;
    private FragmentManager fragmentManager;

    private ConstraintLayout layout_myinfo;
    private RelativeLayout layout_modify;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);

        this.button_modify_myinfo = (Button)findViewById(R.id.modify);
        this.layout_myinfo = (ConstraintLayout)findViewById(R.id.myinfoLayout);
        this.layout_modify = (RelativeLayout)findViewById(R.id.modifyLayout);
        this.img_myinfo = (ImageView)findViewById(R.id.myinfoimg);
        this.change_img = (ImageView)findViewById(R.id.changeimg);
        this.change_pwd = (ImageView)findViewById(R.id.changepwd);
        this.change_back = (ImageView)findViewById(R.id.back);
        this.id_myinfo = (TextView)findViewById(R.id.myinfoid);
        this.name_myinfo = (TextView)findViewById(R.id.myinfoname);



        button_modify_myinfo.setOnClickListener(new onClickModifyMyinfo());
        change_img.setOnClickListener(new onClickChangePic());
        change_pwd.setOnClickListener(new onClickChangePwd());
        change_back.setOnClickListener(new onClickBack());

        layout_modify.setVisibility(GONE);

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.fragment_container, MyInfoFragMain.newInstance()).commitNow();
    }
    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                try {
                    InputStream in = getContentResolver().openInputStream(data.getData());
                    Bitmap img = BitmapFactory.decodeStream(in);
                    in.close();
                    img_myinfo.setImageBitmap(img);
                    change_img.setImageBitmap(img);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class onClickModifyMyinfo implements OnClickListener{

        @Override
        public void onClick(View v){
            layout_modify.setVisibility(View.VISIBLE);
        }
    }
    class onClickChangePic implements OnClickListener {
        @Override
        public void onClick(View v){
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, 1);
        }

    }
    class onClickChangePwd implements OnClickListener {
        @Override
        public void onClick(View v){
            Intent intent=new Intent(v.getContext(),MyInfoChangePassword.class);
            startActivity(intent);
        }
    }

    class onClickBack implements  OnClickListener{
        @Override
        public void onClick(View v){
            layout_modify.setVisibility(GONE);
        }
    }

    @Override
    public void sendData(int data){
        if(data ==1){
            replaceFragment(MyInfoAlarmFrag.newInstance());
        } else if(data ==2){
//            replaceFragment(MyInfoMessageFrag.newInstance());
        } else if(data ==3){
            replaceFragment(MyInfoByeByeFrag.newInstance());
        } else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MyInfoActivity.this);
            alertDialogBuilder.setTitle("로그아웃");
            alertDialogBuilder.setMessage("로그아웃 하시겠습니까?");
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
                            //Toast.makeText(getApplicationContext(),"다시 입력해 주세요",Toast.LENGTH_LONG).show();
                        }
                    });
            alertDialogBuilder.show();
        }
    }

    private void replaceFragment(Fragment fragment) {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.fragment_container, fragment).commit();
    }
}

