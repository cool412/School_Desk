package com.example.schooldesk.calendar_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.schooldesk.R;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.List;

public class GridAdapter extends ArrayAdapter {

    private static final String TAG = GridAdapter.class.getSimpleName();
    private LayoutInflater mInflater;
    private List<Date> monthlyDates;
    private Calendar currentDate;
    private List<EventObjects> allEvents;

    public GridAdapter(Context context, List<Date> monthlyDates, Calendar currentDate, List<EventObjects> allEvents) {
        super(context, R.layout.single_cell_layout);
        this.monthlyDates = monthlyDates;
        this.currentDate = currentDate;
        this.allEvents = allEvents;
        mInflater = LayoutInflater.from(context);
    }

    //TODO. Below is required Android version 'N', which will limit our reach. Need to find some solution

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.N)
    @NonNull
    @Override
    public View getView(int position, View convertView, @NotNull ViewGroup parent) {
        Date mDate = monthlyDates.get(position);
        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(mDate);
        int dayValue = dateCal.get(Calendar.DAY_OF_MONTH);
        int displayMonth = dateCal.get(Calendar.MONTH) + 1;
        int displayYear = dateCal.get(Calendar.YEAR);
        int currentMonth = currentDate.get(Calendar.MONTH) + 1;
        int currentYear = currentDate.get(Calendar.YEAR);
        View view = convertView;
        if (view == null) {
            view = mInflater.inflate(R.layout.single_cell_layout, parent, false);
        }
        if (displayMonth == currentMonth && displayYear == currentYear) {
            view.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            view.setVisibility(View.INVISIBLE);
            //view.setBackgroundColor(Color.parseColor("#CFCECE"));
        }
        //Add day to calendar
        TextView cellNumber = view.findViewById(R.id.calendar_date_id);
        cellNumber.setText(String.valueOf(dayValue));
        //Add events to the calendar
        //TextView eventIndicator = view.findViewById(R.id.event_id);
        Calendar eventCalendar = Calendar.getInstance();
        for (int i = 0; i < allEvents.size(); i++) {
            eventCalendar.setTime(allEvents.get(i).getDate());
            if (dayValue == eventCalendar.get(Calendar.DAY_OF_MONTH) && displayMonth == eventCalendar.get(Calendar.MONTH) + 1
                    && displayYear == eventCalendar.get(Calendar.YEAR)) {
                if (allEvents.get(i).getMessage().equals("present")) {
                    //eventIndicator.setBackgroundColor(R.color.present_color);
                    cellNumber.setBackgroundResource(R.drawable.attendance_present_bg);
                    //eventIndicator.setBackgroundColor(Color.parseColor("#2CDD33"));
                } else if (allEvents.get(i).getMessage().equals("absent")) {
                    //eventIndicator.setBackgroundColor(R.color.absent_color);
                    cellNumber.setBackgroundResource(R.drawable.attendance_abcent_bg);
                    //eventIndicator.setBackgroundColor(Color.parseColor("#EA2B27"));
                }
            }
        }
        return view;
    }

    @Override
    public int getCount() {
        return monthlyDates.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return monthlyDates.get(position);
    }

    @Override
    public int getPosition(Object item) {
        return monthlyDates.indexOf(item);
    }
}
