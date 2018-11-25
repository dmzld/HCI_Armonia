package com.example.user.armonia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MyClubFrag extends Fragment {

    public static MyClubFrag newInstance() {
        return new MyClubFrag();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_myclub, container, false); // 여기서 UI를 생성해서 View를 return
    }

}