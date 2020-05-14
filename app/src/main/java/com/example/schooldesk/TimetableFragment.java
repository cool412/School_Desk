package com.example.schooldesk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.schooldesk.data.ScheduleAdapter;
import com.example.schooldesk.data.SchoolContract;
import com.example.schooldesk.data.VolleySingleton;
import com.example.schooldesk.data.scheduleItems;
import com.example.schooldesk.user.SharedPrefClass;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TimetableFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ScheduleAdapter mExampleAdapter;
    private ArrayList<scheduleItems> mExampleList;
    private RequestQueue mRequestQueue;
    private TextView monTextView, tueTextView, wedTextView, thuTextView, friTextView, satTextView;
    private String mDaySelect = SchoolContract.DAY_MONDAY;

    Context context = getContext();

    //@SuppressLint("StaticFieldLeak")
    //private SharedPrefClass sharedPrefClass = new SharedPrefClass(DashboardActivity());

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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timetable, container, false);

        mRecyclerView = view.findViewById(R.id.time_table_recycler);

        mRecyclerView.setHasFixedSize(true);

        //Below code will assign background to fragment.
        //setAlpha is used to set opacity.
        View backgroundImage = view.findViewById(R.id.time_frag);
        Drawable background = backgroundImage.getBackground();
        background.setAlpha(40);

        // Below line is for making recyclerview horizontal scroll.
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mRequestQueue = VolleySingleton.getInstance(getActivity()).getRequestQueue();

        monTextView = view.findViewById(R.id.day_monday);
        tueTextView = view.findViewById(R.id.day_tuesday);
        wedTextView = view.findViewById(R.id.day_wednesday);
        thuTextView = view.findViewById(R.id.day_thursday);
        friTextView = view.findViewById(R.id.day_friday);
        satTextView = view.findViewById(R.id.day_saturday);

        monTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //parseJSON(SchoolContract.DAY_MONDAY);
                setTextViewColor(SchoolContract.DAY_MONDAY);
            }
        });
        tueTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //parseJSON(SchoolContract.DAY_TUESDAY);
                setTextViewColor(SchoolContract.DAY_TUESDAY);
            }
        });
        wedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //parseJSON(SchoolContract.DAY_WEDNESDAY);
                setTextViewColor(SchoolContract.DAY_WEDNESDAY);
            }
        });
        thuTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //parseJSON(SchoolContract.DAY_THURSDAY);
                setTextViewColor(SchoolContract.DAY_THURSDAY);
            }
        });
        friTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //parseJSON(SchoolContract.DAY_FRIDAY);
                setTextViewColor(SchoolContract.DAY_FRIDAY);
            }
        });
        satTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //parseJSON(SchoolContract.DAY_SATURDAY);
                setTextViewColor(SchoolContract.DAY_SATURDAY);
            }
        });

        /* Below method will help us to remain selected in previously selected day when user rotates screen,
        or any instance where fragment restarts.*/
        if (savedInstanceState != null) {
            mDaySelect = savedInstanceState.getString("savedInst");
            assert mDaySelect != null;
            setTextViewColor(mDaySelect);
        }
        setTextViewColor(mDaySelect);

        return view;
    }
    @Override
    public void onSaveInstanceState(@NotNull Bundle curState){
        super.onSaveInstanceState(curState);
        curState.putString("savedInst", mDaySelect);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /*Below method is for changing the color of text clicked.
    * from here it will also call a method to display respective day schedule.
    * We have to put below line if we are using only color as resource.
    * @SuppressLint("ResourceAsColor")
    * Here we have used XML file so we do not need above line.
    * */
    private void setTextViewColor(String daySelect) {
        mDaySelect = daySelect;

        //monTextView.setBackgroundColor(getResources().getColor(R.color.trans));
        monTextView.setBackgroundResource(0);
        tueTextView.setBackgroundResource(0);
        wedTextView.setBackgroundResource(0);
        thuTextView.setBackgroundResource(0);
        friTextView.setBackgroundResource(0);
        satTextView.setBackgroundResource(0);

        if (daySelect.equals(SchoolContract.DAY_MONDAY))
            monTextView.setBackground(getResources().getDrawable(R.drawable.left_corner));

        if (daySelect.equals(SchoolContract.DAY_TUESDAY))
            tueTextView.setBackground(getResources().getDrawable(R.drawable.center_back));

        if (daySelect.equals(SchoolContract.DAY_WEDNESDAY))
            wedTextView.setBackground(getResources().getDrawable(R.drawable.center_back));

        if (daySelect.equals(SchoolContract.DAY_THURSDAY))
            thuTextView.setBackground(getResources().getDrawable(R.drawable.center_back));

        if (daySelect.equals(SchoolContract.DAY_FRIDAY))
            friTextView.setBackground(getResources().getDrawable(R.drawable.center_back));

        if (daySelect.equals(SchoolContract.DAY_SATURDAY))
            satTextView.setBackground(getResources().getDrawable(R.drawable.right_corner));

        parseJSON(daySelect);
    }


    private void parseJSON(final String daySelect) {
        mExampleList = new ArrayList<>();
        //String url = "http://manage.hydrocarbonz.com/index.php/interfaceclass/getTimetable";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, SchoolContract.TIMETABLE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        try {

                            // We are reading response in JSONObject because we have configured in server like that.
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString(SchoolContract.RESPONSE_KEY).equals(SchoolContract.RESPONSE_SUCCESS)) {
                                if (jsonObject.has(daySelect)) {
                                    JSONArray jsonArray = jsonObject.getJSONArray(daySelect);

                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject hit = jsonArray.getJSONObject(i);

                                        String subject = hit.getString(SchoolContract.SUBJECT_KEY);
                                        String time = hit.getString(SchoolContract.TIME_KEY);
                                        String teacher = hit.getString(SchoolContract.TEACHER_KEY);

                                        mExampleList.add(new scheduleItems(time, subject, teacher));
                                    }
                                    mExampleAdapter = new ScheduleAdapter(getActivity(), mExampleList);
                                    mRecyclerView.setAdapter(mExampleAdapter);
                                } else {
                                    mExampleAdapter = new ScheduleAdapter(getActivity(), mExampleList);
                                    mRecyclerView.setAdapter(mExampleAdapter);
                                }
                            } else {
                                Toast.makeText(getActivity(), "Sorry, There is an invalid response from server.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            // Below method is for sending as data to server over HTTP request.
            // We have to use the same key which we have used in server.
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(SchoolContract.USERNAME_KEY, DashboardActivity.sharedPrefClass.readUserName());
                params.put(SchoolContract.SESSION_SEND_KEY, DashboardActivity.sharedPrefClass.readSessionId());
                params.put(SchoolContract.USER_ROLL_KEY, DashboardActivity.sharedPrefClass.readAccountType());
                return params;
            }
        };
        mRequestQueue.add(stringRequest);
    }
}
