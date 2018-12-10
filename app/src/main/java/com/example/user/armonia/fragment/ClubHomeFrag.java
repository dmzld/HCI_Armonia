package com.example.user.armonia.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.armonia.R;
import com.example.user.armonia.activity.FreeBoardActivity;
import com.example.user.armonia.adapter.AdapterClubPage;
import com.example.user.armonia.adapter.AdapterListPost;
import com.example.user.armonia.list.ListPost;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Collections;

public class    ClubHomeFrag extends Fragment {



    View rootVIew;
    //나중에 db에서 받아와서 저장하면됨
    ImageView imageClubImage;
    TextView textClubName;
    TextView textClubCategory;
    //TextView textClubInfo;
    Button btnClubApply;

    AdapterClubPage adapterClubPage;

    //server
    String myJSON;
    JSONArray list = null;
    private static final String TAG_RESULTS = "result";
    //image
    private static final String TAG_ClubInfo = "ClubInfo";


    static String curEmail="curEmail";
    static String curUser="curUser";
    static String curClubName="curClubName";
    static String curCategory="curCategory";
    String email;
    String user;
    String clubName;
    String category;

    public static ClubHomeFrag newInstance(String email, String user, String clubName, String category) {
        ClubHomeFrag clubHomeFrag = new ClubHomeFrag();
        Bundle args = new Bundle();
        args.putString(curEmail,email);
        args.putString(curUser,user);
        args.putString(curClubName,clubName);
        args.putString(curCategory,category);
        clubHomeFrag.setArguments(args);
        return clubHomeFrag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_club_home, container, false);
        rootVIew = view;
        if(getArguments()!=null){
            email=getArguments().getString(curEmail);
            user=getArguments().getString(curUser);
            clubName=getArguments().getString(curClubName);
            category=getArguments().getString(curCategory);
        }

        imageClubImage=(ImageView)view.findViewById(R.id.clubImage);
        textClubName=(TextView)view.findViewById(R.id.clubName);
        textClubCategory=(TextView)view.findViewById(R.id.clubCategory);
        //textClubInfo=(TextView)view.findViewById(R.id.clubInfo);
        btnClubApply = (Button)view.findViewById(R.id.btnClubApply);

        imageClubImage.setImageResource(R.mipmap.ic_launcher);
        textClubName.setText(clubName);
        textClubCategory.setText(category);


        btnClubApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //신청하면 ~
            }
        });


        //주소
        //클럽이름으로 해당 클럽 내용
        //getData("http://ec2-52-79-235-82.ap-northeast-2.compute.amazonaws.com/armonia/clubHomeFrag.php", clubName);
        return view;
    }

//    protected void showList() {
//        Log.i("log2","log2");
//        try {
//            Log.i("log3","log3");
//
//            JSONObject jsonObj = new JSONObject(myJSON);
//
//            Log.i("log333","log333");
//            list = jsonObj.getJSONArray(TAG_RESULTS);
//
//            Log.i("log4","log4");
//            String ClubInfo =null;
//
//            for (int i = 0; i < list.length(); i++) {
//
//                JSONObject c = list.getJSONObject(i);
//
//                ClubInfo = c.getString(TAG_ClubInfo);
//                Log.i("club_describe",ClubInfo);
//            }
//            TextView Info = (TextView)rootVIew.findViewById(R.id.clubInfo) ;
//            Info.setText("동아리 소개"+"\n"+ClubInfo);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//            Log.i("describe2","describe2");
//        }
//    }
//
//    public void getData(String url, String clubName) {
//        class GetDataJSON extends AsyncTask<String, Void, String> {
//
//            @Override
//            protected String doInBackground(String... params) {
//
//
//                BufferedReader bufferedReader = null;
//                try {
//                    Log.i("logi","logi");
//                    String uri = params[0];
//                    String clubName = params[1];
//                    Log.i("club_name",clubName);
//                    String data = URLEncoder.encode("ClubName","UTF-8")+"="+URLEncoder.encode(clubName,"UTF-8");
//
//                    Log.i("data",data);
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
//                        Log.i("after5",json);
//                    }
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
//                showList();
//            }
//        }
//        GetDataJSON g = new GetDataJSON();
//        g.execute(url,clubName);
//    }


}
