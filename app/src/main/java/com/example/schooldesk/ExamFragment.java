package com.example.schooldesk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldesk.data.ExamAdapter;
import com.example.schooldesk.data.ExamItems;

import java.util.ArrayList;

public class ExamFragment extends Fragment {
    private RecyclerView mRecyclerView;
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
        mExamList = new ArrayList<>();

        String[] sublist1 = {"Mathematics", "Science", "Social Studies"};
        String[] sublist2 = {"Mathematics", "Science", "Social Studies", "Gujarati", "Social Studies", "Hindi"};
        String[] sublist3 = {"Mathematics", "Science", "Social Studies", "Hindi"};
        mExamList.add(new ExamItems("Term-1","Semester Exam",sublist1));
        mExamList.add(new ExamItems("Weekly","Teacher Exam",sublist2));
        mExamList.add(new ExamItems("Regular","Semester Exam",sublist3));
        mExamList.add(new ExamItems("Term-1","NO Exam",sublist1));
        //mExamList.add(new ExamItems("Term-1","Semester Exam","Mathematics2"));
        //mExamList.add(new ExamItems("Term-1","Semester Exam","Mathematics3"));
        //mExamList.add(new ExamItems("Term-1","Semester Exam","Mathematics4"));
        mExamAdapter = new ExamAdapter(getActivity(),mExamList);
        mRecyclerView.setAdapter(mExamAdapter);
        return  view;
    }
}
