package com.example.user.armonia.activity;

import android.content.Intent;
import android.os.AsyncTask;
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
import org.json.JSONArray;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class MakeClubActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView selectedText;
    Spinner spinner;
    String[] item;

    String myJSON;
    JSONArray list = null;

    private String cu;          //동아리/연합 구분 위해 사용
    private String email;
    private String user;

    private EditText club_name;
    private EditText president_name;
    private EditText member_name1;
    private EditText member_name2;
    private EditText member_name3;
    private EditText member_name4;
    private EditText club_content;

    private Button btn_make_club;
    private Button btn_cancel;

    private String spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_club);

        Intent intent = getIntent();
        cu = intent.getStringExtra("cu");
        email = intent.getStringExtra("email");
        user= intent.getStringExtra("student_name");

        //UI생성
        club_name = (EditText)findViewById(R.id.club_name);
        spinner = (Spinner)findViewById(R.id.category);
        president_name = (EditText)findViewById(R.id.president_name);
        member_name1 = (EditText)findViewById(R.id.member_name1);
        member_name2 = (EditText)findViewById(R.id.member_name2);
        member_name3 = (EditText)findViewById(R.id.member_name3);
        member_name4 = (EditText)findViewById(R.id.member_name4);
        club_content = (EditText)findViewById(R.id.club_content);
        btn_make_club = (Button)findViewById(R.id.btn_make_club);
        btn_cancel = (Button)findViewById(R.id.btn_cancel);


        //*********spinner에 표시될 목록*********
        item = new String[]{"선택하세요","스포츠","예술","학술","학생회","사회활동","기타"};

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
                if(spinner.getSelectedItem().toString().equals("선택하세요")){
                    Toast.makeText(MakeClubActivity.this, "분류를 선택하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(president_name.getText().toString().length()==0){
                    Toast.makeText(MakeClubActivity.this, "회장의 이름을 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if((member_name1.getText().toString().length() == 0)||(member_name2.getText().toString().length() == 0)||(member_name3.getText().toString().length() == 0)||(member_name4.getText().toString().length() == 0)){
                    Toast.makeText(MakeClubActivity.this, "동아리원을 모두 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(club_content.getText().toString().length()==0){
                    Toast.makeText(MakeClubActivity.this, "동아리에 대한 간단한 설명을 쓰세요!", Toast.LENGTH_SHORT).show();
                    return;
                }

                spin = spinner.getSelectedItem().toString();
                //함수로 전달
                showData(club_name.getText().toString(),spin,club_content.getText().toString(),president_name.getText().toString(),cu);

//                Intent result = new Intent();
//                result.putExtra("StudentID", student_id);
//
//                // 자신을 호출한 Activity로 데이터를 보낸다.
//                setResult(RESULT_OK, result);
                finish();
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
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
        selectedText.setText(item[i]);
        if(selectedText.getText().toString().equals("선택하세요")){
            return;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView){
        selectedText.setText("");
    }

    //디비와 연동하는 함수
    public void showData(String club_name, String spin, String club_content, String president_name, String cu){

        class InsetData extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected  String doInBackground(String... params)
            {
                try{
                    String club_name = (String)params[0];
                    String spin = (String)params[1];
                    String club_content = (String)params[2];
                    String president_name = (String)params[3];
                    String cu = (String)params[4];


                    String link = "http://ec2-52-79-235-82.ap-northeast-2.compute.amazonaws.com/armonia/makeClub.php";

                    String data = URLEncoder.encode("club_name","UTF-8")+"="+URLEncoder.encode(club_name,"UTF-8");
                    data += "&" + URLEncoder.encode("spin","UTF-8")+"="+URLEncoder.encode(spin,"UTF-8");
                    data += "&" + URLEncoder.encode("club_content","UTF-8")+"="+URLEncoder.encode(club_content,"UTF-8");
                    data += "&" + URLEncoder.encode("president_name","UTF-8")+"="+URLEncoder.encode(president_name,"UTF-8");
                    data += "&" + URLEncoder.encode("cu","UTF-8")+"="+URLEncoder.encode(cu,"UTF-8");

                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write(data);
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    while((line = reader.readLine()) != null)
                    {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                }
                catch(Exception e)
                {
                    return new String("Exception: "+e.getMessage());
                }

            }
        }

        InsetData task = new InsetData();
        task.execute(club_name,spin,club_content,president_name,cu);
    }
}


