package com.example.schooldesk.calendar_view;

import android.content.Context;
import android.icu.util.Calendar;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.schooldesk.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@RequiresApi(api = Build.VERSION_CODES.N)
public class CalendarCustomView extends LinearLayout {
    private static final String TAG = CalendarCustomView.class.getSimpleName();
    private ImageView previousButton, nextButton;
    private TextView currentDate, mTextTotalAttendance, mTextPresentAttendance, mTextAbsentAttendance,
            mTextLeave;
    private GridView calendarGridView;
    private Button addEventButton;
    private static final int MAX_CALENDAR_COLUMN = 42;
    private int month, year;
    private SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
    private Calendar cal = Calendar.getInstance(Locale.ENGLISH);
    private Context context;
    private GridAdapter mAdapter;
    private DatabaseQuery mQuery;

    public CalendarCustomView(Context context) {
        super(context);
    }

    public CalendarCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initializeUILayout();
        setUpCalendarAdapter();
        setPreviousButtonClickEvent();
        setNextButtonClickEvent();
        setGridCellClickEvents();
        Log.d(TAG, "I need to call this method");
    }

    public CalendarCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initializeUILayout() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = Objects.requireNonNull(inflater).inflate(R.layout.calendar_layout, this);
        previousButton = view.findViewById(R.id.previous_month);
        nextButton = view.findViewById(R.id.next_month);
        currentDate = view.findViewById(R.id.display_current_date);
        //addEventButton = view.findViewById(R.id.add_calendar_event);
        calendarGridView = view.findViewById(R.id.calendar_grid);
        //mTextTotalAttendance = view.findViewById(R.id.text_total_attendance);
        mTextPresentAttendance = view.findViewById(R.id.text_present_day);
        mTextAbsentAttendance = view.findViewById(R.id.text_absent_day);
        mTextLeave = view.findViewById(R.id.text_leave_day);
    }

    private void setPreviousButtonClickEvent() {
        previousButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                cal.add(Calendar.MONTH, -1);
                setUpCalendarAdapter();
            }
        });
    }

    private void setNextButtonClickEvent() {
        nextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                cal.add(Calendar.MONTH, 1);
                setUpCalendarAdapter();
            }
        });
    }

    private void setGridCellClickEvents() {
        calendarGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(context, "Clicked " + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setUpCalendarAdapter() {

        List<Date> dayValueInCells = new ArrayList<>();
        //mQuery = new DatabaseQuery(context);
        //List<EventObjects> mEvents = mQuery.getAllFutureEvents();
        List<EventObjects> mEvents = new ArrayList<>();
        DateFormat format = new SimpleDateFormat("d-MM-yyyy", Locale.ENGLISH);
        Date date = new Date();

        /*
        TODO:Here volley implementation is pending to fetch data from server.
            Counting of number of days for presence, absence and leave is also pending.
            Those can be done along with volley implementation.
        */
        mEvents.add(new EventObjects(0, "present", date));
        Date d2 = new Date(120, 4, 18);
        mEvents.add(new EventObjects(0, "absent", d2));
        Date d3 = new Date(120, 3, 18);
        mEvents.add(new EventObjects(0, "leave", d3));

        Calendar mCal = (Calendar) cal.clone();
        //System.out.println(cal.get(Calendar.MONTH));
        //System.out.println(cal.get(Calendar.YEAR));
        mCal.set(Calendar.DAY_OF_MONTH, 1);
        System.out.println(mCal);
        int firstDayOfTheMonth = mCal.get(Calendar.DAY_OF_WEEK) - 1;
        mCal.add(Calendar.DAY_OF_MONTH, -firstDayOfTheMonth);
        while (dayValueInCells.size() < MAX_CALENDAR_COLUMN) {
            dayValueInCells.add(mCal.getTime());
            mCal.add(Calendar.DAY_OF_MONTH, 1);
        }
        Log.d(TAG, "Number of date " + dayValueInCells.size());
        String sDate = formatter.format(cal.getTime());

        currentDate.setText(sDate);
        mAdapter = new GridAdapter(context, dayValueInCells, cal, mEvents);
        calendarGridView.setAdapter(mAdapter);
    }
}