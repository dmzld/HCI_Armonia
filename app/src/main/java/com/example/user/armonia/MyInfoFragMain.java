package com.example.user.armonia;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MyInfoFragMain extends Fragment {

    private ImageView alarm;
    private ImageView byebye;
    private ImageView logout;
    private sendToActivity sendingData;

    public interface sendToActivity{
        void sendData(int data);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof sendToActivity){
            sendingData = (sendToActivity) context;
        } else {
            throw new RuntimeException(context.toString() + "implemnte");
        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        sendingData = null;
    }

    public static MyInfoFragMain newInstance() {
        return new MyInfoFragMain();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myinfo_main, container, false);

        this.alarm =  (ImageView)view.findViewById(R.id.alarm);
        this.byebye = (ImageView)view.findViewById(R.id.byebye);
        this.logout = (ImageView)view.findViewById(R.id.logout);

        alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendingData.sendData(1);
            }
        });
        byebye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendingData.sendData(3);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendingData.sendData(4);
            }
        });
        return view;
    }

}