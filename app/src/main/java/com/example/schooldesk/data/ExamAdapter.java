package com.example.schooldesk.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldesk.R;

import java.util.ArrayList;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ExamViewHolder> {
    private Context mContext;
    private ArrayList<ExamItems> mExamList;

    public ExamAdapter(Context context, ArrayList<ExamItems> examItems) {
        mContext = context;
        mExamList = examItems;
    }

    @NonNull
    @Override
    public ExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.exam_list_card, parent, false);
        return new ExamViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ExamViewHolder holder, int position) {

        ExamItems currentItem = mExamList.get(position);

        String examName = currentItem.getExamName();
        String examDesc = currentItem.getExamDesc();
        String[] examSubject = currentItem.getExamSubject();

        holder.mTextExamName.setText(examName);
        holder.mTextExamDescription.setText(examDesc);
        //holder.mTextExamSubject.setText(examSubject);



        int strLength = examSubject.length;
        TextView[] dynamicTextSubject = new TextView[strLength];
        for (int i = 0; i < strLength; i++){
            dynamicTextSubject[i] = new TextView(mContext);
            dynamicTextSubject[i].setText(examSubject[i]);
            dynamicTextSubject[i].setPaddingRelative(8,8,8,8);
            dynamicTextSubject[i].setBackgroundResource(R.drawable.txt_sub_background);
            Drawable drawable = dynamicTextSubject[i].getContext().getResources().getDrawable(R.drawable.baseline_fiber_manual_record_black_18);
            dynamicTextSubject[i].setCompoundDrawablesWithIntrinsicBounds(drawable,null,null,null);
            dynamicTextSubject[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            holder.mSubjectLayout.addView(dynamicTextSubject[i]);
        }

    }

    @Override
    public int getItemCount() {
        return mExamList.size();
    }


    static class ExamViewHolder extends RecyclerView.ViewHolder {
        TextView mTextExamName, mTextExamDescription, mTextExamSubject;
        LinearLayout mSubjectLayout;
        ExamViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextExamName = itemView.findViewById(R.id.text_exam_name);
            mTextExamDescription = itemView.findViewById(R.id.text_description);
            //mTextExamSubject = itemView.findViewById(R.id.text_subject);
            mSubjectLayout = itemView.findViewById(R.id.subject_layout);
        }
    }
}
