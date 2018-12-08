package com.example.user.armonia.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.armonia.R;


public class CollaboBoardFrag extends Fragment {

    public static CollaboBoardFrag newInstance() { return new CollaboBoardFrag(); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_collabo_board, container, false);
    }
}