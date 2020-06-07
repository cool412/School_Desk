package com.example.schooldesk.teacher;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.schooldesk.R;
import com.example.schooldesk.student.data.ExamAdapter;
import com.example.schooldesk.student.data.ExamItems;
import com.example.schooldesk.student.data.VolleySingleton;
import com.example.schooldesk.teacher.data.AttendanceList;
import com.example.schooldesk.teacher.data.AttendanceListAdapter;
import com.example.schooldesk.user.SchoolContract;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class TakeAttendanceFrag extends Fragment {
    private Spinner classSpinner, subjectSpinner;
    private RecyclerView mRecyclerView;
    private RequestQueue mRequestQueue;
    private AttendanceListAdapter mAttendanceListAdapter;
    private ArrayList<AttendanceList> mAttendanceList;
    private Button mSubmitBtn, mClearBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_take_attendance, container, false);
        classSpinner = view.findViewById(R.id.spin_select_class);
        subjectSpinner = view.findViewById(R.id.spin_select_subject);
        List<String> classLList = new ArrayList<>();
        String str = "Select Class";
        classLList.add(str);
        String str1 = "Class: 1";
        classLList.add(str1);
        String str2 = "Class: 2";
        classLList.add(str2);
        String str3 = "Class: 3";
        classLList.add(str3);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, classLList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSubmitBtn = view.findViewById(R.id.btn_submit_attendance);
        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitDetail();
            }
        });
        mClearBtn = view.findViewById(R.id.btn_clear_attendance);

        classSpinner.setAdapter(adapter);
        classSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectClass = (String) parent.getSelectedItem();
                //display(selectClass);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mRecyclerView = view.findViewById(R.id.attendance_view);
        mRecyclerView.setHasFixedSize(true);
        //final LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        //layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        //mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRequestQueue = VolleySingleton.getInstance(getActivity()).getRequestQueue();
        parseJSON();

        return view;
    }


   /* private void display(String s) {
        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
        List<String> classLList = new ArrayList<>();
        //System.out.println(s);
        switch (s) {
            case "Class: 1": {
                String str = "Select Subject";
                classLList.add(str);
                String str1 = "Maths1";
                classLList.add(str1);
                String str2 = "Science1";
                classLList.add(str2);
                String str3 = "Hindi1";
                classLList.add(str3);
                break;
            }
            case "Class: 2": {
                String str = "Select Subject";
                classLList.add(str);
                String str1 = "Maths2";
                classLList.add(str1);
                String str2 = "Science2";
                classLList.add(str2);
                String str3 = "Hindi2";
                classLList.add(str3);
                break;
            }
            case "Class: 3": {
                String str = "Select Subject";
                classLList.add(str);
                String str1 = "Maths3";
                classLList.add(str1);
                String str2 = "Science3";
                classLList.add(str2);
                String str3 = "Hindi3";
                classLList.add(str3);
                break;
            }
            default:
                String str = "Select Subject";
                classLList.add(str);
                break;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(),
                android.R.layout.simple_spinner_item, classLList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectSpinner.setAdapter(adapter);
    }*/

    private void parseJSON() {
        mAttendanceList = new ArrayList<>();
        mAttendanceList.add(new AttendanceList("1", "kul", SchoolContract.ATTENDANCE_PRESENT));
        mAttendanceList.add(new AttendanceList("2", "deep", SchoolContract.ATTENDANCE_ABSENT));
        mAttendanceList.add(new AttendanceList("3", "dk", SchoolContract.ATTENDANCE_NO_ENTRY));
        mAttendanceList.add(new AttendanceList("4", "tk", SchoolContract.ATTENDANCE_PRESENT));
        mAttendanceList.add(new AttendanceList("5", "tkj", SchoolContract.ATTENDANCE_ABSENT));
        mAttendanceList.add(new AttendanceList("3", "dk1", SchoolContract.ATTENDANCE_NO_ENTRY));
        mAttendanceList.add(new AttendanceList("4", "tk1", SchoolContract.ATTENDANCE_PRESENT));
        mAttendanceList.add(new AttendanceList("5", "tkj1", SchoolContract.ATTENDANCE_ABSENT));

        mAttendanceListAdapter = new AttendanceListAdapter(getContext(), mAttendanceList);
        mRecyclerView.setAdapter(mAttendanceListAdapter);
        //onCustomClick();
    }

    private void submitDetail() {
        int present = 0, absent = 0, na = 0;
        for (int i = 0; i < mAttendanceList.size(); i++) {
            if (mAttendanceList.get(i).getmAttendanceFlag().equals(SchoolContract.ATTENDANCE_PRESENT))
                present++;
            else if (mAttendanceList.get(i).getmAttendanceFlag().equals(SchoolContract.ATTENDANCE_ABSENT))
                absent++;
            else na++;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Attendance Detail");
        String displayDialog = "Present: " + present
                + "\nAbsent: " + absent
                + "\nNo Entri: " + na;
        builder.setMessage(displayDialog);
        builder.setCancelable(true);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}