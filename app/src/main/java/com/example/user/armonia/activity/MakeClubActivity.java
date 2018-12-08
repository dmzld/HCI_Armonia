package com.example.user.armonia.activity;

import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.armonia.R;

public class MakeClubActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView selectedText;
    Spinner spinner;
    String[] item;

    //아직 전부는 사용 안함
    private EditText club_name;
    private EditText president_name;
    private EditText president_id_num;
    private EditText member_name1;
    private EditText member_name2;
    private EditText member_name3;
    private EditText member_name4;

    private Button btn_make_club;
    private Button btn_cancel;
    private Button btn_add_more;

    private String spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_club);

        club_name = (EditText)findViewById(R.id.club_name);
        spinner = (Spinner)findViewById(R.id.category);
        president_name= (EditText)findViewById(R.id.president_id_num);
        president_id_num = (EditText)findViewById(R.id.president_id_num);
        member_name1 = (EditText)findViewById(R.id.member_name1);
        member_name2 = (EditText)findViewById(R.id.member_name2);
        member_name3 = (EditText)findViewById(R.id.member_name3);
        member_name4 = (EditText)findViewById(R.id.member_name4);

        btn_make_club = (Button)findViewById(R.id.btn_make_club);
        btn_cancel = (Button)findViewById(R.id.btn_cancel);
//      btn_add_more = (Button)findViewById(R.id.btn_add_more);

        //spinner에 표시될 목록
        item = new String[]{"선택하세요","축구","음악","오락","학생회","농구"};

        //spinner 생성코드
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        //개설신청을 눌렀을 때 예외처리
        btn_make_club.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(club_name.getText().toString().length()==0){
                    Toast.makeText(MakeClubActivity.this, "동아리명을 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(spin.equals("선택하세요")){
                    Toast.makeText(MakeClubActivity.this, "분류를 선택하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(president_name.getText().toString().length()==0){
                    Toast.makeText(MakeClubActivity.this, "회장의 이름을 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(president_id_num.getText().toString().length()==0){
                    Toast.makeText(MakeClubActivity.this, "회장의 학번을 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if((member_name1.getText().toString().length() == 0)||(member_name2.getText().toString().length() == 0)||(member_name3.getText().toString().length() == 0)||(member_name4.getText().toString().length() == 0)){
                    Toast.makeText(MakeClubActivity.this, "동아리원을 모두 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        //취소를 눌렀을때 수행 기대값
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //수정 요망
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
        selectedText.setText(item[i]);
        if(selectedText.getText().toString().equals("선택하세요")){
            return;
        }
    }
    //수정 요망
    @Override
    public void onNothingSelected(AdapterView<?> adapterView){ selectedText.setText(""); }
}

