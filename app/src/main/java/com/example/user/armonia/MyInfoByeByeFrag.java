package com.example.user.armonia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MyInfoByeByeFrag extends Fragment {

    private Button back;
    private Button byebye;

    public static MyInfoByeByeFrag newInstance() {
        return new MyInfoByeByeFrag();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myinfo_byebye, container, false);

        this.back = (Button)view.findViewById(R.id.back);
        this.byebye = (Button)view.findViewById(R.id.byebye);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().remove(MyInfoByeByeFrag.this).commit();
                fragmentManager.popBackStack();
            }
        });
        byebye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().remove(MyInfoByeByeFrag.this).commit();
                fragmentManager.popBackStack();
            }
        });

        return view;
    }

}