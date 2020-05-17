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

import com.example.schooldesk.data.ExamAdapter;
import com.example.schooldesk.data.ExamItems;

import java.io.Serializable;
import java.util.ArrayList;

public class ExamFragment extends Fragment implements Serializable{
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
        });
        return view;
    }
}
