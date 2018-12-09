package com.example.user.armonia.fragment;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.user.armonia.R;
import com.example.user.armonia.adapter.AdapterTaskList;
import com.example.user.armonia.list.ListTask;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


public class ClubCalendarFrag extends Fragment {

    private ConstraintLayout task;
    private EditText setTitle;
    private EditText setContent;
    private Button setConfirm;
    private Button close;
    public String title;
    public String content;
    private ListView tasklist;
    ArrayList<ListTask> tasks;
    AdapterTaskList adapterTaskList;

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

        this.task = (ConstraintLayout)view.findViewById(R.id.task);
        this.setTitle = (EditText) view.findViewById(R.id.setTitle);
        this.setContent = (EditText) view.findViewById(R.id.setContent);
        this.setConfirm = (Button)view.findViewById(R.id.setConfirm);
        this.close = (Button)view.findViewById(R.id.close);
        this.tasklist = (ListView)view.findViewById(R.id.taskList);



        final MaterialCalendarView materialCalendarView = (MaterialCalendarView)view.findViewById(R.id.calendar);
        materialCalendarView.addDecorators(
                new SundayDecorator(),
                new SaturdayDecorator(),
                new OneDayDecorator());
        this.task.setVisibility(GONE);
        materialCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {
                final HashSet<CalendarDay> dates = new HashSet<>();
                dates.add(date);

                task.setVisibility(VISIBLE);

                setConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        materialCalendarView.addDecorator(new EventDecorator(Color.RED, dates));
                        Toast.makeText(getActivity(), "완료", Toast.LENGTH_SHORT).show();
                        title = setTitle.getText().toString();
                        content = setContent.getText().toString();
                        ArrayList<ListTask> list = new ArrayList<>();
                        ListTask item1 = new ListTask(title, content);
                        list.add(item1);
                        adapterTaskList = new AdapterTaskList(getContext(), list);
                        tasklist.setAdapter(adapterTaskList);



                        task.setVisibility(GONE);
                        setTitle.setText("");
                        setContent.setText("");
                    }
                });
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        task.setVisibility(GONE);
                    }
                });
            }

        });
        return view;
    }


    public class EventDecorator implements DayViewDecorator {

        private final int color;
        private final HashSet<CalendarDay> dates;

        public EventDecorator(int color, Collection<CalendarDay> dates) {
            this.color = color;
            this.dates = new HashSet<>(dates);
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new DotSpan(10, color));
        }
    }


    private void savePreferences(String key, String value){
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);

        editor.apply();
    }
    public class SundayDecorator implements DayViewDecorator {

        private final Calendar calendar = Calendar.getInstance();

        public SundayDecorator() {
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            day.copyTo(calendar);
            int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
            return weekDay == Calendar.SUNDAY;
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new ForegroundColorSpan(Color.RED));
        }
    }
    public class SaturdayDecorator implements DayViewDecorator {

        private final Calendar calendar = Calendar.getInstance();

        public SaturdayDecorator() {
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            day.copyTo(calendar);
            int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
            return weekDay == Calendar.SATURDAY;
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new ForegroundColorSpan(Color.BLUE));
        }
    }

    public class OneDayDecorator implements DayViewDecorator {

        private CalendarDay date;

        public OneDayDecorator() {
            date = CalendarDay.today();
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return date != null && day.equals(date);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new StyleSpan(Typeface.BOLD));
            view.addSpan(new RelativeSizeSpan(1.4f));
            view.addSpan(new ForegroundColorSpan(Color.GREEN));
        }
        public void setDate(Date date) {
            this.date = CalendarDay.from(date);
        }
    }

    public Runnable calendarupater = new Runnable() {
        @Override
        public void run() {
        }
    };
}
