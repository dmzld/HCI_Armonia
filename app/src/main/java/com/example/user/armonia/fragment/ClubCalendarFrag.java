package com.example.user.armonia.fragment;

import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;
import com.example.user.armonia.R;
import com.example.user.armonia.activity.MainActivity;
import static com.facebook.FacebookSdk.getApplicationContext;


public class ClubCalendarFrag extends Fragment {

    public static ClubCalendarFrag newInstance() {
        return new ClubCalendarFrag();
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_club_calendar, container, false);


        CalendarView calendar = (CalendarView)view.findViewById(R.id.calendar);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            public void onSelectedDayChange(CalendarView view, int year,
                                            int month, int dayOfMonth) {


                Toast.makeText(getActivity(), "" + year + "/" +
                        (month + 1) + "/" + dayOfMonth, 0).show();
            }

        });

        return view;
    }

}
