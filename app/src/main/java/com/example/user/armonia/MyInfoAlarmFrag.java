package com.example.user.armonia;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MyInfoAlarmFrag extends Fragment {

    private Button back;
    private TextView type1;
    private TextView type2;
    private TextView type3;
    private TextView getHow;

    public int pushType = 0;           //0=소리, 1=진동, 2=무음
    public int[] pushes = {0, 0, 0};   //0=받음, 1=안받음


    public static MyInfoAlarmFrag newInstance() {
        return new MyInfoAlarmFrag();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myinfo_alarm, container, false);

        this.back = (Button)view.findViewById(R.id.back);
        this.type1 = (TextView) view.findViewById(R.id.type1);
        this.type2 = (TextView)view.findViewById(R.id.type2);
        this.type3 = (TextView)view.findViewById(R.id.type3);
        this.getHow = (TextView)view.findViewById(R.id.getHow);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().remove(MyInfoAlarmFrag.this).commit();
                fragmentManager.popBackStack();
            }
        });

        type1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] types = {"받음", "받지않음"};
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setTitle("푸시 받으시겠어요?");
                alertDialogBuilder.setItems(types,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                type1.setText(types[which]);
                                dialog.dismiss();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        type2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] types = {"받음", "받지않음"};
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setTitle("푸시 받으시겠어요?");
                alertDialogBuilder.setItems(types,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                type2.setText(types[which]);
                                dialog.dismiss();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        type3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] types = {"받음", "받지않음"};
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setTitle("푸시 받으시겠어요?");
                alertDialogBuilder.setItems(types,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                type3.setText(types[which]);
                                dialog.dismiss();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
        getHow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final CharSequence[] typesHow = {"무음", "진동", "소리"};
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                alertDialogBuilder.setTitle("푸시 종류 선택");
                alertDialogBuilder.setItems(typesHow,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                getHow.setText(typesHow[which]);
                                dialog.dismiss();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });



        return view;
    }

}