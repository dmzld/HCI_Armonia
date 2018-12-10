package com.example.user.armonia.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.user.armonia.R;
import com.example.user.armonia.adapter.AdapterListPost;
import com.example.user.armonia.list.ListPost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class FreeBoardActivity extends AppCompatActivity {

    String email;
    String user;

    ListView listFreeView;
    AdapterListPost adapterListFree;
    ArrayList<ListPost> freeArrayList;
    Button btnWrite;

    //server
    String myJSON;
    private static final String TAG_RESULTS = "result";
    private static final String TAG_title = "Title";
    private static final String TAG_name = "User";
    private static final String TAG_date = "Date";
    private static final String TAG_content = "Content";
    JSONArray list = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_board);

        //intent 받은거 받아야함
        Intent intent = getIntent();
        email = intent.getStringExtra("email");
        user = intent.getStringExtra("user");

        //전체 자유게시판
        listFreeView = (ListView)findViewById(R.id.listFreeView);
        freeArrayList = new ArrayList<ListPost>();


        btnWrite = (Button)findViewById(R.id.btnWrite);
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),WritePostActivity.class);
                intent.putExtra("email",email);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

        listFreeView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(), PostActivity.class);
                //intent.putExtra();
                startActivity(intent);
            }
        });


        //주소
        getData("http://ec2-52-79-235-82.ap-northeast-2.compute.amazonaws.com/armonia/freeBoard.php");
    }

    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            list = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < list.length(); i++) {
                JSONObject c = list.getJSONObject(i);
                String title = c.getString(TAG_title);
                String name = c.getString(TAG_name);
                String date = c.getString(TAG_date);
                String content = c.getString(TAG_content);
              ListPost freePost = new ListPost(title,name,date,content);
                freeArrayList.add(freePost);
            }
            Collections.reverse(freeArrayList);
            adapterListFree = new AdapterListPost(FreeBoardActivity.this,freeArrayList);
            listFreeView.setAdapter(adapterListFree);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getData(String url) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }

                    return sb.toString().trim();

                } catch (Exception e) {
                    return null;
                }
            }
            @Override
            protected void onPostExecute(String result) {
                myJSON = result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }
}
