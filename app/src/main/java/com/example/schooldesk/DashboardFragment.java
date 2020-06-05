package com.example.schooldesk;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldesk.user.SharedPrefClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Objects;


public class DashboardFragment extends Fragment {
    private TextView mTextExam;
    private TextView mTextAttendance;
    private LinearLayout mLinearExamSchedule, mLinearTimeTable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        TextView mTextProfile = view.findViewById(R.id.text_profile);
        TextView mTextName = view.findViewById(R.id.text_student_name);
        mTextName.setText(SharedPrefClass.getInstance(getContext()).getUserFullName());

        mLinearTimeTable = view.findViewById(R.id.layout_time_table);
        mLinearTimeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new TimetableFragment()).addToBackStack(null).commit();
            }
        });

        mLinearExamSchedule = view.findViewById(R.id.layout_exam_schedule);
        mLinearExamSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ExamFragment()).addToBackStack(null).commit();
            }
        });
        return view;
    }
}
