package com.example.schooldesk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.example.schooldesk.data.ExamDetailAdapter;
import com.example.schooldesk.data.ExamDetailItem;
import com.example.schooldesk.data.ExamItems;
import com.example.schooldesk.data.VolleySingleton;

import java.io.Serializable;
import java.util.ArrayList;

public class ExamDetailActivity extends AppCompatActivity  {
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
        //TextView text = findViewById(R.id.text);

        //assert examItems != null;
        //text.setText(examItems.getExamName());

        mTotalMarks = findViewById(R.id.text_marks_scored);
        mTotalResult = findViewById(R.id.text_result);
        mPercentage = findViewById(R.id.text_percentage);
        mSubjectAppeared = findViewById(R.id.text_subject_appeared);

        mRecyclerView = findViewById(R.id.exam_detail_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRequestQueue = VolleySingleton.getInstance(this).getRequestQueue();
        loadExamDetail();
    }

    private void loadExamDetail(){
        mExamDetailList = new ArrayList<>();

        mExamDetailList.add(new ExamDetailItem("Mathematics", "16", "Apr", "10:00 AM",
                "50", "48", "Algebra Weekly Exam","pass"));
        mExamDetailList.add(new ExamDetailItem("Science", "17", "Apr", "11:00 AM",
                "50", "45", "Environment Weekly Exam", "pass"));
        mExamDetailList.add(new ExamDetailItem("Hindi", "18", "Apr", "12:00 PM",
                "50", "40", "Raju Comic Weekly Exam","pass"));
        mExamDetailList.add(new ExamDetailItem("Gujarati", "20", "Apr", "01:00 PM",
                "50", "42", "Jay Jay Garwi Weekly Exam","fail"));
        mExamDetailList.add(new ExamDetailItem("Social Study", "15", "Apr", "02:00 PM",
                "50", "45", "Mughal Emperor Weekly Exam","fail"));

        mExamDetailAdapter = new ExamDetailAdapter(this,mExamDetailList);
        mRecyclerView.setAdapter(mExamDetailAdapter);
    }
}