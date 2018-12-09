package com.example.user.armonia.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.armonia.R;
import com.example.user.armonia.activity.FreeBoardActivity;
import com.example.user.armonia.adapter.AdapterListPost;
import com.example.user.armonia.list.ListPost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;

public class ClubHomeFrag extends Fragment {

    //나중에 db에서 받아와서 저장하면됨
    ImageView imageClubImage;
    TextView textClubName;
    TextView textClubCategory;
    TextView textClubDescribe;
    Button btnClubApply;

    //server
    String myJSON;
    private static final String TAG_RESULTS = "result";
    //image
    private static final String TAG_clubName = "clubName";
    private static final String TAG_clubCategory = "category";
    private static final String TAG_clubDescribe = "clubInfo";
    JSONArray list = null;

    public static ClubHomeFrag newInstance() {
        return new ClubHomeFrag();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_club_home, container, false);

        imageClubImage=(ImageView)view.findViewById(R.id.clubImage);
        textClubName=(TextView)view.findViewById(R.id.clubName);
        textClubCategory=(TextView)view.findViewById(R.id.clubCategory);
        textClubDescribe=(TextView)view.findViewById(R.id.clubDesc);
        btnClubApply = (Button)view.findViewById(R.id.btnClubApply);


        btnClubApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //신청하면 ~
            }
        });


        //주소
        getData("http://ec2-52-79-235-82.ap-northeast-2.compute.amazonaws.com/armonia/ClubHomeFage.php");
        return view;
    }

    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            list = jsonObj.getJSONArray(TAG_RESULTS);

            //image
            String clubName = null;
            String clubCategory = null;
            String clubDescribe = null;

            for (int i = 0; i < list.length(); i++) {
                JSONObject c = list.getJSONObject(i);
                //image
                clubName = c.getString(TAG_clubName);
                clubCategory = c.getString(TAG_clubCategory);
                clubDescribe = c.getString(TAG_clubDescribe);
            }

            //image
            textClubName.setText(clubName);
            textClubCategory.setText(clubCategory);
            textClubDescribe.setText(clubDescribe);


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
