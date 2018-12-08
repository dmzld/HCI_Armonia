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

import com.example.user.armonia.R;

public class MakeClubActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView selectedText;
    Spinner spinner;
    String[] item;

    //아직 전부는 사용 안함
    private EditText club_name;
    private EditText president_name;
    private EditText president_id_num;
    private Button btn_make_club;
    private Button btn_cancel;
    private Button btn_add_more;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_club);

        club_name = (EditText)findViewById(R.id.club_name);
        president_name= (EditText)findViewById(R.id.president_id_num);
        president_id_num = (EditText)findViewById(R.id.president_id_num);
        btn_make_club = (Button)findViewById(R.id.button_make_club);
        btn_cancel = (Button)findViewById(R.id.btn_cancel);
        btn_add_more = (Button)findViewById(R.id.btn_add_more);
        spinner = (Spinner)findViewById(R.id.category);

        //spinner에 표시될 목록
        item = new String[]{"선택하세요","축구","음악","오락","학생회","농구"};

        //spinner 생성코드
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

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
            selectedText.setText("");
        }
    }
    //수정 요망
    @Override
    public void onNothingSelected(AdapterView<?> adapterView){
        selectedText.setText("");
    }
}

