package com.example.schooldesk.teacher.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldesk.R;
import com.example.schooldesk.user.SchoolContract;

import java.util.ArrayList;

public class AttendanceListAdapter extends RecyclerView.Adapter<AttendanceListAdapter.AttendanceViewHolder> {
    private Context mContext;
    private ArrayList<AttendanceList> mAttendanceList;

    //below is constructor for this class.
    public AttendanceListAdapter(Context mContext, ArrayList<AttendanceList> mAttendanceList) {
        this.mContext = mContext;
        this.mAttendanceList = mAttendanceList;
    }

    @NonNull
    @Override
    public AttendanceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.attendance_card, parent, false);
        return new AttendanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AttendanceViewHolder holder, int position) {
        final AttendanceList currentItem = mAttendanceList.get(position);
        String id = currentItem.getmStudentId();
        String name = currentItem.getmStudentName();
        String attendance = currentItem.getmAttendanceFlag();

        holder.mTextId.setText(id);
        holder.mTextName.setText(name);
        if (attendance.equals(SchoolContract.ATTENDANCE_PRESENT))
            holder.mRadioPresent.setChecked(true);
        else if (attendance.equals(SchoolContract.ATTENDANCE_ABSENT))
            holder.mRadioAbsent.setChecked(true);
        else
            holder.mRadioNA.setChecked(true);

        //Below Method look after the changes made by user in the attendance.
        holder.mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = group.findViewById(checkedId);
                currentItem.setmAttendanceFlag((String) rb.getText());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAttendanceList.size();
    }


    static class AttendanceViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextId, mTextName;
        private RadioGroup mRadioGroup;
        private RadioButton mRadioPresent, mRadioAbsent, mRadioNA;

        public AttendanceViewHolder(@NonNull final View itemView) {
            super(itemView);
            mTextId = itemView.findViewById(R.id.text_id);
            mTextName = itemView.findViewById(R.id.text_st_name);
            mRadioGroup = itemView.findViewById(R.id.radio_gr);
            mRadioPresent = itemView.findViewById(R.id.rb_present);
            mRadioAbsent = itemView.findViewById(R.id.rb_absent);
            mRadioNA = itemView.findViewById(R.id.rb_noentry);

        }
    }
}
