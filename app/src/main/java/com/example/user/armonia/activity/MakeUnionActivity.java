package com.example.user.armonia.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MakeUnionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    TextView selectedText;
    Spinner spinner;        //내가 속한 동아리목록
    String[] item;          //spinner 1 목록
    String myJSON;

    private String cu;          //동아리/연합 구분 위해 사용
    private EditText union_name;
    private EditText president_name;
    private EditText union_content;
    private Button btn_make_union;
    private Button btn_cancel;

    //spin값 받아오기 위해 사용
    private String spin;
    JSONArray list = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_union);

        Intent intent = getIntent();
        cu = intent.getStringExtra("cu");

        union_name = (EditText)findViewById(R.id.union_name);
        president_name = (EditText)findViewById(R.id.president_name);
        spinner = (Spinner)findViewById(R.id.my_club);
        union_content = (EditText)findViewById(R.id.union_content);
        btn_make_union = (Button)findViewById(R.id.btn_make_union);
        btn_cancel = (Button)findViewById(R.id.btn_cancel);


        //********수정필요******spinner에 나타날 인자(?)들
        item = new String[]{"선택하세요","볼랜드","한터","5분쉼표","학생회","안시"};

        //spinner생성 코드
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


  //      getData("http://52.11.180.128/dbProject/6_1.php","");




        btn_make_union.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(union_name.getText().toString().length()==0){
                    Toast.makeText(MakeUnionActivity.this, "연합명을 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(president_name.getText().toString().length()==0){
                    Toast.makeText(MakeUnionActivity.this, "회장의 학번을 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(spinner.getSelectedItem().toString().equals("선택하세요")){
                    Toast.makeText(MakeUnionActivity.this, "소속 동아리를 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(union_content.getText().toString().length()==0){
                    Toast.makeText(MakeUnionActivity.this, "연합 설명을 입력하세요!", Toast.LENGTH_SHORT).show();
                    return;
                }

                spin = spinner.getSelectedItem().toString();

                //함수로 전달
                showData(union_name.getText().toString(), spin, union_content.getText().toString(), president_name.getText().toString(),cu);
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

//
//    public void getData(String url, String category) {
//        class GetDataJSON extends AsyncTask<String, Void, String> {
//
//            @Override
//            protected String doInBackground(String... params) {
//
//
//                BufferedReader bufferedReader = null;
//                try {
//                    String uri = params[0];
//                    String category = params[1];
//                    String data = URLEncoder.encode("category","UTF-8")+"="+URLEncoder.encode(category,"UTF-8");
//                    URL url = new URL(uri);
//                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
//
//                    con.setDoOutput(true);
//                    OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
//
//                    wr.write(data);
//                    wr.flush();
//
//                    StringBuilder sb = new StringBuilder();
//
//                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//
//                    String json;
//                    while ((json = bufferedReader.readLine()) != null) {
//                        sb.append(json + "\n");
//                    }
//                    Log.i("after","after");
//
//                    return sb.toString().trim();
//
//                } catch (Exception e) {
//                    return null;
//                }
//
//
//            }
//
//            @Override
//            protected void onPostExecute(String result) {
//                myJSON = result;
//            }
//        }
//        GetDataJSON g = new GetDataJSON();
//        g.execute(url,category);
//    }


    //디비와 연동하는 함수
    public void showData(String union_name, String spin, String union_content, String president_name, String cu){

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
                    String union_name = (String)params[0];
                    String spin = (String)params[1];
                    String union_content = (String)params[2];
                    String president_name = (String)params[3];
                    String cu = (String)params[4];


                    String link = "http://ec2-52-79-235-82.ap-northeast-2.compute.amazonaws.com/armonia/makeUnion.php";

                    String data = URLEncoder.encode("union_name","UTF-8")+"="+URLEncoder.encode(union_name,"UTF-8");
                    data += "&" + URLEncoder.encode("spin","UTF-8")+"="+URLEncoder.encode(spin,"UTF-8");
                    data += "&" + URLEncoder.encode("union_content","UTF-8")+"="+URLEncoder.encode(union_content,"UTF-8");
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
        task.execute(union_name,spin,union_content,president_name,cu);
    }



    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
        selectedText.setText(item[i]);
        if(selectedText.getText().toString().equals("선택하세요")){
            selectedText.setText("");
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView){
        selectedText.setText("");
    }
}