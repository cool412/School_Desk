package com.example.schooldesk.data;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldesk.R;

import java.util.ArrayList;

public class ExamDetailAdapter extends RecyclerView.Adapter<ExamDetailAdapter.ExamDetailViewHolder> {
    private Context mContext;
    private ArrayList<ExamDetailItem> mExamDetailList;

    public ExamDetailAdapter(Context context, ArrayList<ExamDetailItem> examDetailItems){
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
        holder.mTextTime.setText(currentItem.getTimeOfExam());
        holder.mTextTotalMarks.setText(currentItem.getMarks());
        //holder.mTextObtainedMarks.setText(currentItem.getObtainedMarks());
        holder.mTextDescription.setText(currentItem.getExamDescription());
        String result = currentItem.getResult();
        String setText = "Result: "+result;
        if (result.equals("pass")){
            holder.mTextResult.setBackgroundResource(R.drawable.text_pass_back);
        } else if (result.equals("fail")){
            holder.mTextResult.setBackgroundResource(R.drawable.text_fail_back);
        }
        holder.mTextResult.setText(setText);
    }

    @Override
    public int getItemCount() {
        return mExamDetailList.size();
    }

    static class ExamDetailViewHolder extends RecyclerView.ViewHolder{
        TextView mTextSubject, mTextDay, mTextMonth, mTextTime, mTextResult, mTextPercentage,
                mTextTotalMarks, mTextObtainedMarks, mTextDescription;

        ExamDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextSubject = itemView.findViewById(R.id.text_detail_subject);
            mTextDay = itemView.findViewById(R.id.text_exam_day);
            mTextMonth = itemView.findViewById(R.id.text_exam_month);
            mTextTime = itemView.findViewById(R.id.text_exam_time);
            mTextResult = itemView.findViewById(R.id.text_detail_result);
            //mTextPercentage = itemView.findViewById(R.id.text_detail_percentage);
            mTextTotalMarks = itemView.findViewById(R.id.text_detail_total);
            //mTextObtainedMarks = itemView.findViewById(R.id.text_detail_marks_obtained);
            mTextDescription = itemView.findViewById(R.id.text_detail_exam_desc);
        }
    }
}
