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

public class MakeUnionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    TextView selectedText;
    Spinner spinner;        //내가 속한 동아리목록
    Spinner spinner2;       //연합하고자 하는 동아리목록
    String[] item;          //spinner 1 목록
    String[] item2;         //spinner 2 목록

    //아직 전부는 구현안함
    private EditText union_name;
//    private Button btnAdd;
    private EditText union_content;
    private Button btn_make_union;
    private Button btn_cancel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_union);

        union_name = (EditText)findViewById(R.id.union_name);
        spinner = (Spinner)findViewById(R.id.my_club);
        spinner2 = (Spinner)findViewById(R.id.add_union);
        union_content = (EditText)findViewById(R.id.union_content);
        btn_make_union = (Button)findViewById(R.id.btn_make_union);
        btn_cancel = (Button)findViewById(R.id.btn_cancel);
//        btnAdd = (Button)findViewById(R.id.btnAdd);

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
//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//
//            }
//        });

        btn_make_union.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(union_name.getText().toString().length()==0){
                    Toast.makeText(MakeUnionActivity.this, "연합명을 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(spinner.getSelectedItem().toString().equals("선택하세요")){
                    Toast.makeText(MakeUnionActivity.this, "소속 동아리를 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(spinner2.getSelectedItem().toString().equals("선택하세요")){
                    Toast.makeText(MakeUnionActivity.this, "연합할 동아리명을 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if(union_content.getText().toString().length()==0){
                    Toast.makeText(MakeUnionActivity.this, "연합 설명을 입력하세요!", Toast.LENGTH_SHORT).show();
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