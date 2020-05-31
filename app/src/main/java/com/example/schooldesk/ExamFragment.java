package com.example.schooldesk;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
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
import com.android.volley.toolbox.StringRequest;
import com.example.schooldesk.data.ExamAdapter;
import com.example.schooldesk.data.ExamItems;
import com.example.schooldesk.data.ScheduleAdapter;
import com.example.schooldesk.data.SchoolContract;
import com.example.schooldesk.data.VolleySingleton;
import com.example.schooldesk.data.scheduleItems;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExamFragment extends Fragment implements Serializable {
    private RecyclerView mRecyclerView;
    private RequestQueue mRequestQueue;
    private ExamAdapter mExamAdapter;
    private ArrayList<ExamItems> mExamList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exam, container, false);
        //return super.onCreateView(inflater, container, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.exam_table_recycler);
        mRecyclerView.setHasFixedSize(true);
        //final LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        //layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRequestQueue = VolleySingleton.getInstance(getActivity()).getRequestQueue();

        parseJSON();
/*
        String[] sublist1 = {"Mathematics", "Science", "Social Studies"};
        String[] sublist2 = {"Mathematics", "Science", "Social Studies", "Gujarati", "Social Studies", "Hindi"};
        String[] sublist3 = {"Mathematics", "Science", "Social Studies", "Hindi"};
        mExamList.add(new ExamItems("Term-1", "Semester Exam", sublist1, "08-B", "15",
                "Mar", "18", "Mar"));
        mExamList.add(new ExamItems("Weekly", "Teacher Exam", sublist2, "07-A", "11",
                "Apr", "15", "Apr"));
        mExamList.add(new ExamItems("Regular", "Semester Exam", sublist3, "09-C", "12",
                "Feb", "16", "Feb"));
        mExamList.add(new ExamItems("Term-1", "NO Exam", sublist1, "08-B", "15",
                "Mar", "18", "Mar"));
        //mExamList.add(new ExamItems("Term-1","Semester Exam","Mathematics2"));
        //mExamList.add(new ExamItems("Term-1","Semester Exam","Mathematics3"));
        //mExamList.add(new ExamItems("Term-1","Semester Exam","Mathematics4"));
        mExamAdapter = new ExamAdapter(getActivity(), mExamList);
        mRecyclerView.setAdapter(mExamAdapter);
        mExamAdapter.setOnItemClickListener(new ExamAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getContext(), "Item clicked" + position, Toast.LENGTH_SHORT).show();

                // Here we are getting an clicked item.
                ExamItems clickedExamItem = mExamList.get(position);
                Intent intent = new Intent(getContext(), ExamDetailActivity.class);
                // passing an object to the other activity.
                // We have to use Serializable for that. We must implements Serializable in all parent and child class.
                // Here in our case we have to implement Serializable in ExamFragment.java and ExamItem.java
                intent.putExtra("clickExam", (Serializable) clickedExamItem);
                startActivity(intent);
            }
        });*/
        return view;
    }

    private void parseJSON() {
        mExamList = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, SchoolContract.EXAM_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println(response);
                        try {
                            // We are reading response in JSONObject because we have configured in server like that.
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString(SchoolContract.RESPONSE_KEY).equals(SchoolContract.RESPONSE_SUCCESS)) {
                                JSONArray jsonArray = jsonObject.getJSONArray(SchoolContract.EXAM_LIST_KEY);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject hit = jsonArray.getJSONObject(i);
                                    String examId = hit.getString(SchoolContract.EXAM_ID_KEY);
                                    String examName = hit.getString(SchoolContract.EXAM_NAME_KEY);
                                    String examDescription = hit.getString(SchoolContract.EXAM_DESCRIPTION_KEY);
                                    String startDate = hit.getString(SchoolContract.EXAM_START_DATE_KEY);
                                    String endDate = hit.getString(SchoolContract.EXAM_END_DATE_KEY);
                                    JSONArray jsonSubjectArray = hit.getJSONArray(SchoolContract.EXAM_SUBJECT_KEY);
                                    System.out.println(jsonSubjectArray);
                                    String[] str = new String[jsonSubjectArray.length()];
                                    for (int j = 0; j < jsonSubjectArray.length(); j++) {
                                        JSONObject hitSubject = jsonSubjectArray.getJSONObject(j);
                                        str[j] = hitSubject.getString(SchoolContract.EXAM_SUBJECT_NAME_KEY);
                                    }

                                    //TODO: Class Number is still pending.
                                    mExamList.add(new ExamItems(examId, examName, examDescription, str, "5", startDate, endDate));
                                }
                                mExamAdapter = new ExamAdapter(getActivity(), mExamList);
                                mRecyclerView.setAdapter(mExamAdapter);
                                onClickCustom();
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

    private void onClickCustom() {
        mExamAdapter.setOnItemClickListener(new ExamAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getContext(), "Item clicked" + position, Toast.LENGTH_SHORT).show();

                // Here we are getting an clicked item.
                ExamItems clickedExamItem = mExamList.get(position);
                Intent intent = new Intent(getContext(), ExamDetailActivity.class);
                // passing an object to the other activity.
                // We have to use Serializable for that. We must implements Serializable in all parent and child class.
                // Here in our case we have to implement Serializable in ExamFragment.java and ExamItem.java
                intent.putExtra("clickExam", (Serializable) clickedExamItem);
                startActivity(intent);
            }
        });
    }
}
