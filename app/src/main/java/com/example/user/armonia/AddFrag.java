package com.example.user.armonia;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Intent;

public class AddFrag extends Fragment{

    private Button button_make_club;
    Button button_make_union;
    Button button_my_info;

    public static AddFrag newInstance() {
        return new AddFrag();
    }
    /*
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_add, container, false);
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

*/
}
