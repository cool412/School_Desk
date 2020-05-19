package com.example.schooldesk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.example.schooldesk.calendar_view.CalendarCustomView;
import com.example.schooldesk.data.ScheduleAdapter;
import com.example.schooldesk.data.scheduleItems;

import java.util.ArrayList;

public class ScheduleFragment extends Fragment {
    private RecyclerView mRecyclerView, mRecyclerViewTuesday;
    private ScheduleAdapter mExampleAdapter, mExampleAdapterTuesday;
    private ArrayList<scheduleItems> mExampleList, mExampleListTuesday;
    private RequestQueue mRequestQueue;
    private ImageView showHideView;
    private ProgressBar progressBar;
    private static final String TAG = DashboardActivity.class.getSimpleName();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @SuppressLint("UseRequireInsteadOfGet")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        CalendarCustomView mView = view.findViewById(R.id.attendance_calendar);
        //Below code is to set title. But still needs some improvement.
        //Objects.requireNonNull(getActivity()).setTitle("Time Table");
        /* We have also implemented other classes to view data.
         * ScheduleAdapter, scheduleItems.
         * ScheduleAdapter is an adapter to display contents.
         * scheduleItems contains the type of data which we are going to display.
         * We have created an array of scheduleItems for storing data.*/
//ndjfnjsd
        /*
        showHideView = view.findViewById(R.id.hide_show_monday);
        showHideView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeViewVisible();
            }
        });

        CalendarView calendarView = view.findViewById(R.id.calender_attendance);



        mRecyclerView = view.findViewById(R.id.time_recycler_view);
        mRecyclerViewTuesday = view.findViewById(R.id.time_recycler_view_tuesday);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerViewTuesday.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        LinearLayoutManager linearLayoutManagerTuesday = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        mRecyclerViewTuesday.setLayoutManager(linearLayoutManagerTuesday);

        mExampleList = new ArrayList<>();
        mExampleListTuesday = new ArrayList<>();

        //mRequestQueue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));
        mRequestQueue = VolleySingleton.getInstance(getActivity()).getRequestQueue();
        //parseJSON();*/
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
/*
    private void parseJSON() {
        //String url = "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";
        String url = "http://manage.hydrocarbonz.com/index.php/interfaceclass/getTimetable";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            //JSONArray jsonArray = response.getJSONArray("hits");
                            JSONArray jsonArray = response.getJSONArray("Monday");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                String subject = hit.getString("subject");
                                String time = hit.getString("time");
                                String teacher = hit.getString("teacher");
                                //String creatorName = hit.getString("user");
                                //String imageUrl = hit.getString("webformatURL");
                                //int likeCount = hit.getInt("likes");

                                //mExampleList.add(new scheduleItems(imageUrl, creatorName, likeCount));
                                mExampleList.add(new scheduleItems(time, subject, teacher));
                            }
                            JSONArray jsonArrayTue = response.getJSONArray("Tuesday");

                            for (int i = 0; i < jsonArrayTue.length(); i++) {
                                JSONObject hit = jsonArrayTue.getJSONObject(i);

                                String subject = hit.getString("subject");
                                String time = hit.getString("time");
                                String teacher = hit.getString("teacher");
                                //String creatorName = hit.getString("user");
                                //String imageUrl = hit.getString("webformatURL");
                                //int likeCount = hit.getInt("likes");

                                //mExampleList.add(new scheduleItems(imageUrl, creatorName, likeCount));
                                mExampleListTuesday.add(new scheduleItems(time, subject, teacher));
                            }
                            //mExampleListTuesday

                            mExampleAdapter = new ScheduleAdapter(getActivity(), mExampleList);
                            mExampleAdapterTuesday = new ScheduleAdapter(getActivity(), mExampleListTuesday);
                            mRecyclerView.setAdapter(mExampleAdapter);
                            mRecyclerViewTuesday.setAdapter(mExampleAdapterTuesday);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }

    private void makeViewVisible(){
        if(mRecyclerView.getVisibility() == View.VISIBLE){
            mRecyclerView.setVisibility(View.GONE);
            showHideView.setImageResource(R.drawable.baseline_keyboard_arrow_left_black_24dp);
        } else if (mRecyclerView.getVisibility() == View.GONE){
            mRecyclerView.setVisibility(View.VISIBLE);
            showHideView.setImageResource(R.drawable.baseline_keyboard_arrow_down_black_24dp);
        }
    }*/
}
