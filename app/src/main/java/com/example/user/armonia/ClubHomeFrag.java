package com.example.user.armonia;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ClubHomeFrag extends Fragment {

    //나중에 db에서 받아와서 저장하면됨
    ImageView clubImage;
    TextView clubName;
    TextView clubCategory;
    TextView clubDesc;
    Button btnClubApply;


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

        clubImage=(ImageView)view.findViewById(R.id.clubImage);
        clubName=(TextView)view.findViewById(R.id.clubName);
        clubCategory=(TextView)view.findViewById(R.id.clubCategory);
        clubDesc=(TextView)view.findViewById(R.id.clubDesc);
        btnClubApply = (Button)view.findViewById(R.id.btnClubApply);

        /*나중에 DB받아와서
        clubImage.setImageResource();
        clubName.setText();
        clubCategory.setText();
        clubDesc.setText();
         */

        btnClubApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //신청하면 ~
            }
        });

        return view;
    }

}
