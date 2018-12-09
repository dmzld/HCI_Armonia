package com.example.user.armonia.fragment;

import android.content.Intent;
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

import java.util.ArrayList;

public class ClubBoardFrag extends Fragment {

    public static ClubBoardFrag newInstance() {
        return new ClubBoardFrag();
    }
    Button btnWrite;
    ListView listPostView;
    AdapterListPost adapterListPost;
    ArrayList<ListPost> postArrayList;

    //server
    String myJSON;
    private static final String TAG_RESULTS = "result";
    private static final String TAG_title = "Title";
    private static final String TAG_name = "User";
    private static final String TAG_date = "Date";
    JSONArray list = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_club_board, container, false);

        listPostView = (ListView)view.findViewById(R.id.listClubBoardView);
        postArrayList = new ArrayList<ListPost>();
        btnWrite = (Button)view.findViewById(R.id.btnWrite);
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),WritePostActivity.class);
                startActivity(intent);
            }
        });


        listPostView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getActivity(), PostActivity.class);
                //지금은 일단 그냥 클럽 액티비티로
                //intent.putExtra();
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
                ListPost clubPost = new ListPost(title,name,date);
                postArrayList.add(clubPost);
            }
            Collections.reverse(postArrayList);
            adapterListPost = new AdapterListPost(getActivity(),postArrayList);
            listPostView.setAdapter(adapterListPost);
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
