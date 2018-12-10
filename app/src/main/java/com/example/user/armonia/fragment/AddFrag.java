package com.example.user.armonia.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Intent;

import com.example.user.armonia.activity.MakeClubActivity;
import com.example.user.armonia.activity.MakeUnionActivity;
import com.example.user.armonia.activity.MyInfoActivity;
import com.example.user.armonia.R;

public class AddFrag extends Fragment{

    static String curEmail="curEmail";
    static String curUser="curUser";
    String email;
    String user;

    private Button button_make_club;
    Button button_make_union;
    Button button_my_info;

    public static AddFrag newInstance(String email,String user) {
        AddFrag addFrag = new AddFrag();
        Bundle args = new Bundle();
        args.putString(curEmail,email);
        args.putString(curUser,user);
        addFrag.setArguments(args);
        return addFrag;
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_add, container, false);

        if(getArguments()!=null){
            email=getArguments().getString(curEmail);
            user=getArguments().getString(curUser);
        }


        button_make_club = (Button)view.findViewById(R.id.button_make_club);
        button_make_union = (Button)view.findViewById(R.id.button_make_union);
        button_my_info = (Button)view.findViewById(R.id.button_my_info);

        button_make_club.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getContext(),MakeClubActivity.class);
                startActivity(intent);
            }
        });

        button_make_union.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getContext(),MakeUnionActivity.class);
                intent.putExtra("studentEmail",email);
                intent.putExtra("studentName",user);
                startActivity(intent);
            }
        });

        button_my_info.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getContext(),MyInfoActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }


}
