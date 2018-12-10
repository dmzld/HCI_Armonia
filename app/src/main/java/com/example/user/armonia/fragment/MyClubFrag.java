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

import com.example.user.armonia.activity.ClubPageActivity;
import com.example.user.armonia.activity.FreeBoardActivity;
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

public class MyClubFrag extends Fragment {

    static String curEmail="curEmail";
    static String curUser="curUser";
    String email;
    String user;

    public static MyClubFrag newInstance(String email, String user) {
        MyClubFrag myClubFrag = new MyClubFrag();
        Bundle args = new Bundle();
        args.putString(curEmail,email);
        args.putString(curUser,user);
        myClubFrag.setArguments(args);
        return myClubFrag;
    }


    ListView listClubView;
    AdapterListClub adapterListClub_my;
    ArrayList<ListClub> listClubArrayList;

    //server
    String myJSON;
    private static final String TAG_RESULTS = "result";
    private static final String TAG_clubName = "ClubName";
    private static final String TAG_category = "Category";
    private static final String TAG_clubInfo = "ClubInfo";

    JSONArray list = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //RelativeLayout myClubFrag = (RelativeLayout)inflater.inflate(R.layout.frag_myclub, container, false);
        View view = inflater.inflate(R.layout.frag_myclub, container, false);

        if(getArguments()!=null){
            email=getArguments().getString(curEmail);
            user=getArguments().getString(curUser);
        }
        Log.i("username1","username : "+user);
        listClubView = (ListView)view.findViewById(R.id.list_myClub);
        listClubArrayList = new ArrayList<ListClub>();


        listClubView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent = new Intent(getActivity(), ClubPageActivity.class);
                //db로 클릭한 동아리 정보를 같이 건네서 해당 동아리 페이지로 가야함
                //지금은 일단 그냥 클럽 액티비티로
                intent.putExtra("email",email);
                intent.putExtra("user",user);
                intent.putExtra("clubName",listClubArrayList.get(position).getClub_name());
                intent.putExtra("clubCategory",listClubArrayList.get(position).getClub_category());
                intent.putExtra("clubInfo",listClubArrayList.get(position).getClub_info());

                startActivity(intent);
            }
        });



        Log.i("username2",user);
        //주소
        getData("http://ec2-52-79-235-82.ap-northeast-2.compute.amazonaws.com/armonia/myClubFrag.php",user);

        return view; // 여기서 UI를 생성해서 View를 return
    }

    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            list = jsonObj.getJSONArray(TAG_RESULTS);

            listClubArrayList.clear();
            for (int i = 0; i < list.length(); i++) {
                JSONObject c = list.getJSONObject(i);
                //image
                String clubName = c.getString(TAG_clubName);
                String category = c.getString(TAG_category);
                String clubInfo = c.getString(TAG_clubInfo);
                ListClub myClub = new ListClub(R.mipmap.ic_launcher,clubName,category,clubInfo);
                listClubArrayList.add(myClub);
            }
            adapterListClub_my = new AdapterListClub(getActivity(),listClubArrayList);
            listClubView.setAdapter(adapterListClub_my);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void getData(String url,String user) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {


                BufferedReader bufferedReader = null;
                try {

                    String uri = params[0];
                    String user = params[1];
                    String data = URLEncoder.encode("user","UTF-8")+"="+URLEncoder.encode(user,"UTF-8");

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
        g.execute(url,user);
    }


}