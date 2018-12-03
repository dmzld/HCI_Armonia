package com.example.user.armonia;

import android.os.Bundle;
import android.view.View;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MakeUnionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    TextView selectedText;
    Spinner spinner;        //내가 속한 동아리목록
    Spinner spinner2;       //연합하고자 하는 동아리목록
    String[] item;          //spinner 1 목록
    String[] item2;         //spinner 2 목록

    //아직 전부는 구현안함
    private EditText club_name;
    private EditText union_content;
    private Button btnAdd;
    private Button btn_cancel;
    private Button btn_make_union;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_union);


        spinner = (Spinner)findViewById(R.id.my_club);
        spinner2 = (Spinner)findViewById(R.id.add_union);
        btn_cancel = (Button)findViewById(R.id.btn_cancel);
        btnAdd = (Button)findViewById(R.id.btnAdd);

        //spinner에 나타날 인자(?)들
        item = new String[]{"선택하세요","볼랜드","한터","5분쉼표","학생회","안시"};
        item2 = new String[]{"선택하세요","소울","혜윰","총학","ABBA"};

        //spinner생성 코드
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        ArrayAdapter <String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,item2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);


        //*****추가해야됨****
        btnAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

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

    //나중에 수정야함
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
        selectedText.setText(item[i]);
        if(selectedText.getText().toString().equals("선택하세요")){
            selectedText.setText("");
        }
    }
    //이것 역시 수정해야함
    @Override
    public void onNothingSelected(AdapterView<?> adapterView){
        selectedText.setText("");
    }
}