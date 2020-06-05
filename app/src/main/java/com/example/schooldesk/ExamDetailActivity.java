package com.example.schooldesk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.schooldesk.data.ExamAdapter;
import com.example.schooldesk.data.ExamDetailAdapter;
import com.example.schooldesk.data.ExamDetailItem;
import com.example.schooldesk.data.ExamItems;
import com.example.schooldesk.data.SchoolContract;
import com.example.schooldesk.data.VolleySingleton;
import com.example.schooldesk.user.SharedPrefClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ExamDetailActivity extends AppCompatActivity {
    TextView mTotalMarks, mTotalResult, mPercentage, mSubjectAppeared;
    private RecyclerView mRecyclerView;
    private ExamDetailAdapter mExamDetailAdapter;
    private ArrayList<ExamDetailItem> mExamDetailList;
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_detail);
        // Getting a detail from previous activity.
        Intent intent = getIntent();
        Serializable serializableExtra = intent.getSerializableExtra("clickExam");
        ExamItems examItems = (ExamItems) serializableExtra;
        String id = Objects.requireNonNull(examItems).getExamId();

        String examDescription = examItems.getExamDesc();
        TextView textExamDescription = findViewById(R.id.text_exam_description);
        textExamDescription.setText(examDescription);

        //TODO... I am still showing student name from fix text. need to do something for that.
        TextView mTextName = findViewById(R.id.text_student_name);
        mTextName.setText(SharedPrefClass.getInstance(getApplicationContext()).getUserFullName());
        
        mTotalMarks = findViewById(R.id.text_marks_scored);
        mTotalResult = findViewById(R.id.text_result);
        mSubjectAppeared = findViewById(R.id.text_subject_appeared);

        mRecyclerView = findViewById(R.id.exam_detail_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRequestQueue = VolleySingleton.getInstance(this).getRequestQueue();
        loadExamDetail(id);
    }

    private void loadExamDetail(final String examId) {
        mExamDetailList = new ArrayList<>();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, SchoolContract.EXAM_DETAIL_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //System.out.println(response);
                        try {
                            // We are reading response in JSONObject because we have configured in server like that.
                            JSONObject jsonObject = new JSONObject(response);
                            if (jsonObject.getString(SchoolContract.RESPONSE_KEY).equals(SchoolContract.RESPONSE_SUCCESS)) {
                                String totalScore = jsonObject.getString(SchoolContract.TOTAL_SCORE_KEY);
                                String subjectAppeared = jsonObject.getString(SchoolContract.SUBJECT_APPEARED_KEY);
                                String overAllResult = jsonObject.getString(SchoolContract.OVERALL_RESULT_KRY);
                                String percentage = jsonObject.getString(SchoolContract.PERCENTAGE_SCORE_KEY);
                                displayText(totalScore, subjectAppeared, overAllResult, percentage);
                                JSONArray jsonArray = jsonObject.getJSONArray(SchoolContract.SUBJECT_WISE_LIST_KEY);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject hit = jsonArray.getJSONObject(i);
                                    String subjectName = hit.getString(SchoolContract.SUBJECT_NAME_KEY);
                                    String dayOfMonth = hit.getString(SchoolContract.SUBJECT_DAY_OF_MONTH);
                                    String month = hit.getString(SchoolContract.SUBJECT_MONTH);
                                    String year = hit.getString(SchoolContract.SUBJECT_YEAR);
                                    String totalMarks = hit.getString(SchoolContract.SUBJECT_TOTAL_MARKS);
                                    String obtainedMarks = hit.getString(SchoolContract.SUBJECT_OBTAINED_MARKS);
                                    String passingMarks = hit.getString(SchoolContract.SUBJECT_PASSING_MARKS);
                                    String startTime = hit.getString(SchoolContract.SUBJECT_START_TIME);
                                    String endTime = hit.getString(SchoolContract.SUBJECT_END_TIME);
                                    String examResult = hit.getString(SchoolContract.SUBJECT_RESULT_KEY);

                                    mExamDetailList.add(new ExamDetailItem(subjectName, dayOfMonth, month, year,
                                            totalMarks, obtainedMarks, passingMarks, startTime, endTime, examResult));
                                }
                                mExamDetailAdapter = new ExamDetailAdapter(getApplicationContext(), mExamDetailList);
                                mRecyclerView.setAdapter(mExamDetailAdapter);

                            } else {
                                Toast.makeText(getApplicationContext(), "Sorry, There is an invalid response from server.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            // Below method is for sending as data to server over HTTP request.
            // We have to use the same key which we have used in server.
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put(SchoolContract.USERNAME_KEY, SharedPrefClass.getInstance(getApplicationContext()).readUserName());
                params.put(SchoolContract.SESSION_SEND_KEY, SharedPrefClass.getInstance(getApplicationContext()).readSessionId());
                params.put(SchoolContract.USER_ROLL_KEY, SharedPrefClass.getInstance(getApplicationContext()).readAccountType());
                params.put(SchoolContract.EXAM_ID_KEY, examId);
                return params;
            }
        };
        mRequestQueue.add(stringRequest);
    }

    private void displayText(String total, String appeared, String result, String percentage) {
        mTotalResult.setText(result);
        mSubjectAppeared.setText(appeared);
        String dispResult = total + " (" + percentage + ")";
        mTotalMarks.setText(dispResult);
    }
}