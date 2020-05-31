package com.example.schooldesk.data;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldesk.R;

import java.util.ArrayList;

public class ExamDetailAdapter extends RecyclerView.Adapter<ExamDetailAdapter.ExamDetailViewHolder> {
    private Context mContext;
    private ArrayList<ExamDetailItem> mExamDetailList;

    public ExamDetailAdapter(Context context, ArrayList<ExamDetailItem> examDetailItems) {
        mContext = context;
        mExamDetailList = examDetailItems;
    }

    @NonNull
    @Override
    public ExamDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.exam_detail_card, parent, false);
        return new ExamDetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamDetailViewHolder holder, int position) {
        ExamDetailItem currentItem = mExamDetailList.get(position);

        holder.mTextSubject.setText(currentItem.getSubject());
        holder.mTextDay.setText(currentItem.getDayOfExam());
        holder.mTextMonth.setText(currentItem.getMonthOfExam());
        holder.mTextYear.setText(currentItem.getYearOfExam());
        holder.mTextExamTime.setText(currentItem.getExamDuration());

        String result = currentItem.getResult();
        String setText = "Result: " + result;
        //TODO... Background for pending exam and result is still pending.
        //Convert to switch statement if possible.
        //TODO... One thing is missing, what if the student is absent?
        if (result.equals(SchoolContract.SUBJECT_RESULT_PASS_RESPONSE)) {
            holder.mTextResult.setBackgroundResource(R.drawable.text_pass_back);
            holder.mTextObtainedMarks.setVisibility(View.GONE);
            holder.vewSeparator.setVisibility(View.GONE);
            holder.mTextTotalMarks.setText(currentItem.getMarks());
            holder.mTextTotalMarks.setTextColor(Color.parseColor("#25BF0E"));
        } else if (result.equals(SchoolContract.SUBJECT_RESULT_FAIL_RESPONSE)) {
            holder.mTextResult.setBackgroundResource(R.drawable.text_fail_back);
            holder.mTextObtainedMarks.setVisibility(View.GONE);
            holder.vewSeparator.setVisibility(View.GONE);
            holder.mTextTotalMarks.setText(currentItem.getMarks());
            holder.mTextTotalMarks.setTextColor(Color.parseColor("#E31F2F"));
        } else if (result.equals(SchoolContract.SUBJECT_RESULT_EXAM_PENDING_RESPONSE) ||
                result.equals(SchoolContract.SUBJECT_RESULT_NOT_DECLARE_RESPONSE)) {
            holder.mTextResult.setBackgroundResource(R.drawable.text_awaited_back);
            holder.mTextTotalMarks.setText(currentItem.getTotalMarks());
            holder.mTextObtainedMarks.setText(currentItem.getPassingMarks());
        }
        holder.mTextResult.setText(setText);
    }

    @Override
    public int getItemCount() {
        return mExamDetailList.size();
    }

    static class ExamDetailViewHolder extends RecyclerView.ViewHolder {
        TextView mTextSubject, mTextDay, mTextMonth, mTextYear, mTextResult, mTextPercentage,
                mTextTotalMarks, mTextObtainedMarks, mTextExamTime;
        View vewSeparator;

        ExamDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextSubject = itemView.findViewById(R.id.text_detail_subject);
            mTextDay = itemView.findViewById(R.id.text_exam_day);
            mTextMonth = itemView.findViewById(R.id.text_exam_month);
            mTextYear = itemView.findViewById(R.id.text_exam_year);
            mTextResult = itemView.findViewById(R.id.text_detail_result);
            mTextTotalMarks = itemView.findViewById(R.id.text_detail_total);
            mTextObtainedMarks = itemView.findViewById(R.id.text_detail_marks_obtained);
            mTextExamTime = itemView.findViewById(R.id.text_detail_exam_time);
            vewSeparator = itemView.findViewById(R.id.view_separator);
        }
    }
}
