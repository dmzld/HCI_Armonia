package com.example.user.armonia.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import android.widget.ListView;

import android.widget.SearchView;

import com.example.user.armonia.activity.ClubPageActivity;
import com.example.user.armonia.adapter.AdapterListPost;
import com.example.user.armonia.list.ListClub;
import com.example.user.armonia.R;
import com.example.user.armonia.adapter.AdapterListClub;
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
import java.util.ArrayList;
import java.util.Collections;


public class SearchFrag extends Fragment {

    SearchView searchClub;
    Button btnAll;
    Button btnStudent;
    Button btnStudy;
    Button btnSport;
    Button btnArt;
    Button btnSocial;
    Button btnEtc;
    private String category="0";

    //server
    String myJSON;
    private static final String TAG_RESULTS = "result";

    private static final String TAG_clubName = "ClubName";
    private static final String TAG_category = "Category";
    JSONArray list = null;

    static String curEmail="curEmail";
    static String curUser="curUser";
    String email;
    String user;

    public static SearchFrag newInstance(String email, String user) {
        SearchFrag searchFrag = new SearchFrag();
        Bundle args = new Bundle();
        args.putString(curEmail,email);
        args.putString(curUser,user);
        searchFrag.setArguments(args);
        return searchFrag;
    }

    ListView listClubView;
    AdapterListClub adapaterListClub;
    ArrayList<ListClub> listClubArrayList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);


        btnAll = (Button) view.findViewById(R.id.btnAll);
        btnStudent = (Button) view.findViewById(R.id.btnStudent);
        btnStudy = (Button) view.findViewById(R.id.btnStudy);
        btnSport = (Button) view.findViewById(R.id.btnSport);
        btnArt = (Button) view.findViewById(R.id.btnArt);
        btnSocial = (Button) view.findViewById(R.id.btnSocial);
        btnEtc = (Button) view.findViewById(R.id.btnEtc);

        listClubView = (ListView) view.findViewById(R.id.list_club);
        listClubArrayList = new ArrayList<ListClub>();



        listClubView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getActivity(), ClubPageActivity.class);
                //db로 클릭한 동아리 정보를 같이 건네서 해당 동아리 페이지로 가야함
                //지금은 일단 그냥 클럽 액티비티로
                intent.putExtra("club_name", "볼랜드");
                startActivity(intent);
            }
        });

        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getData("http://ec2-52-79-235-82.ap-northeast-2.compute.amazonaws.com/armonia/searchAllClub.php",category);
            }
        });

        btnStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listClubView.clearAnimation();
                category="학생회";
                getData("http://ec2-52-79-235-82.ap-northeast-2.compute.amazonaws.com/armonia/searchClub.php",category);
            }
        });

        btnStudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category = "학술";
                getData("http://ec2-52-79-235-82.ap-northeast-2.compute.amazonaws.com/armonia/searchClub.php",category);
            }
        });

        btnSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category = "스포츠";
                getData("http://ec2-52-79-235-82.ap-northeast-2.compute.amazonaws.com/armonia/searchClub.php",category);
            }
        });

        btnArt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category = "예술";
                getData("http://ec2-52-79-235-82.ap-northeast-2.compute.amazonaws.com/armonia/searchClub.php",category);
            }
        });

        btnSocial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category = "사회활동";
                getData("http://ec2-52-79-235-82.ap-northeast-2.compute.amazonaws.com/armonia/searchClub.php",category);
            }
        });

        btnEtc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                category="기타";
                getData("http://ec2-52-79-235-82.ap-northeast-2.compute.amazonaws.com/armonia/searchClub.php",category);
            }
        });

        return view;

    }

    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            list = jsonObj.getJSONArray(TAG_RESULTS);

            listClubArrayList.clear();
            for (int i = 0; i < list.length(); i++) {
                JSONObject c = list.getJSONObject(i);

                String clubName = c.getString(TAG_clubName);
                String category = c.getString(TAG_category);

                ListClub lists = new ListClub(R.mipmap.ic_launcher, clubName, category);
                listClubArrayList.add(lists);
            }
            adapaterListClub = new AdapterListClub(getActivity(), listClubArrayList);
            listClubView.setAdapter(adapaterListClub);
            //adapaterListClub.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getData(String url, String category) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                BufferedReader bufferedReader = null;
                try {

                    String uri = params[0];
                    String category = params[1];
                    String data = URLEncoder.encode("category","UTF-8")+"="+URLEncoder.encode(category,"UTF-8");
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    con.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());

                    wr.write(data);
                    wr.flush();

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
        g.execute(url,category);


    }

}
