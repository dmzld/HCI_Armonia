package com.example.user.armonia.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.user.armonia.R;
import com.example.user.armonia.activity.PostActivity;
import com.example.user.armonia.activity.WritePostActivity;
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


public class CollaboBoardFrag extends Fragment {

    static String curEmail="curEmail";
    static String curUser="curUser";
    String email;
    String user;

    public static CollaboBoardFrag newInstance(String email, String user) {
        CollaboBoardFrag collaboBoardFrag = new CollaboBoardFrag();
        Bundle args = new Bundle();
        args.putString(curEmail,email);
        args.putString(curUser,user);
        collaboBoardFrag.setArguments(args);
        return collaboBoardFrag;
    }

    //server
    String myJSON;
    private static final String TAG_RESULTS = "result";
    private static final String TAG_title = "Title";
    private static final String TAG_name = "User";
    private static final String TAG_date = "Date";
    private static final String TAG_content = "Content";
    JSONArray list = null;

    Button btnWrite;
    ListView listCollaView;
    AdapterListPost adapterListColla;
    ArrayList<ListPost> collaArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_collabo_board, container, false);

        listCollaView = (ListView)view.findViewById(R.id.listCollaView);
        collaArrayList = new ArrayList<ListPost>();
        btnWrite = (Button)view.findViewById(R.id.btnWrite);
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),WritePostActivity.class);
                startActivity(intent);
            }
        });


        adapterListColla = new AdapterListPost(getActivity(),collaArrayList);
        listCollaView.setAdapter(adapterListColla);

        listCollaView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent = new Intent(getActivity(), PostActivity.class);
                intent.putExtra("Title",collaArrayList.get(position).getPost_Title());
                intent.putExtra("Date",collaArrayList.get(position).getPost_Date());
                intent.putExtra("User",collaArrayList.get(position).getPost_Name());
                intent.putExtra("Content",collaArrayList.get(position).getPost_content());
                startActivity(intent);
            }
        });

        //주소
        getData("http://ec2-52-79-235-82.ap-northeast-2.compute.amazonaws.com/armonia/clubBoard.php");

        return view;
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
                ListPost clubPost = new ListPost(title,"작성일"+"\n"+date,"작성자"+"\n"+name,"내용"+"\n"+content);
                collaArrayList.add(clubPost);
            }
            Collections.reverse(collaArrayList);
            adapterListColla = new AdapterListPost(getActivity(),collaArrayList);
            listCollaView.setAdapter(adapterListColla);
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