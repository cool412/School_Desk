package com.example.schooldesk.student.data;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Html;
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
    private OnItemClickListener mListener;

    // Below is an interface for click listener.
    // We will implement in ExamFragment.java class.
    public interface OnItemClickListener {
        // Method is declared here and implemented in parent class.
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    //Below is constructor for class.
    public ExamAdapter(Context context, ArrayList<ExamItems> examItems) {
        mContext = context;
        mExamList = examItems;
    }

    @NonNull
    @Override
    public ExamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.exam_list_card, parent, false);
        return new ExamViewHolder(view, mListener);
    }


    @Override
    public void onBindViewHolder(@NonNull ExamViewHolder holder, int position) {
        ExamItems currentItem = mExamList.get(position);

        String examName = currentItem.getExamName();
        String examDesc = currentItem.getExamDesc();
        String[] examSubject = currentItem.getExamSubject();
        String examClass = currentItem.getClassNumber();
        String dateRange = currentItem.getExamDateRange();

        holder.mTextExamName.setText(examName);
        holder.mTextExamDescription.setText(examDesc);
        //holder.mTextExamSubject.setText(examSubject);
        holder.mTextExamClass.setText(examClass);
        holder.mExamDateRange.setText(dateRange);

        if (position%2 == 0){
            holder.mMainLayout.setBackgroundResource(R.drawable.exam_bg);
        }else{
            holder.mMainLayout.setBackgroundResource(R.drawable.exam_bg1);
        }

        int strLength = examSubject.length;
        //System.out.println(strLength);
        StringBuilder textSubject = new StringBuilder();
        //System.out.println(textSubject);
        if (strLength > 0) {
            for (String s : examSubject) {
                textSubject.append("<font color=#0e8bc0><i>").append(s).append("</i></font>").append(" <strong>|</strong> ");
                //textSubject.append("<span style='border: 1px powderblue;padding: 2px; border-radius:8px;background-color:powderblue;'>").
                //        append(s).append(" </span>");
            }
            String str = textSubject.toString();
            holder.mTextExamSubject.setText(Html.fromHtml(str.substring(0, str.length() - 19)));
            //holder.mTextExamSubject.setText(Html.fromHtml(String.valueOf(textSubject.append("</body></html>"))));
        } else {
            holder.mTextExamSubject.setText(R.string.no_subject_string);
        }

        //TODO: If there is not subject then what to show is pending.
        /*
        TextView[] dynamicTextSubject = new TextView[strLength];
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < strLength; i++) {
            //text.append(" <font color=#cc0029>|</font> ").append(examSubject[i]);
            //text.append(" <li> ").append(examSubject[i]).append("</li>");
            dynamicTextSubject[i] = new TextView(mContext);
            dynamicTextSubject[i].setText(examSubject[i]);
            dynamicTextSubject[i].setPaddingRelative(8, 8, 8, 8);
            dynamicTextSubject[i].setBackgroundResource(R.drawable.txt_sub_background);
            Drawable drawable = dynamicTextSubject[i].getContext().getResources().getDrawable(R.drawable.baseline_fiber_manual_record_black_18);
            dynamicTextSubject[i].setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
            dynamicTextSubject[i].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            holder.mSubjectLayout.addView(dynamicTextSubject[i]);
        }
        System.out.println(text);
        dynamicTextSubject[0] = new TextView(mContext);
        dynamicTextSubject[0].setText(Html.fromHtml(String.valueOf(text)));
        dynamicTextSubject[0].setPadding(8,8,8,8);
        dynamicTextSubject[0].setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        holder.mSubjectLayout.addView(dynamicTextSubject[0]);
         */
    }

    @Override
    public int getItemCount() {
        return mExamList.size();
    }

    // static class extended by RecyclerView.ViewHolder
    static class ExamViewHolder extends RecyclerView.ViewHolder {
        TextView mTextExamName, mTextExamDescription, mTextExamSubject, mTextExamClass, mExamDateRange;
        LinearLayout mSubjectLayout, mMainLayout;

        // Constructor of this static class is also having final object of OnItemClickListener.
        ExamViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mTextExamName = itemView.findViewById(R.id.text_exam_name);
            mTextExamDescription = itemView.findViewById(R.id.text_description);
            mTextExamSubject = itemView.findViewById(R.id.text_subject);
            mSubjectLayout = itemView.findViewById(R.id.subject_layout);
            mTextExamClass = itemView.findViewById(R.id.text_class);
            mExamDateRange = itemView.findViewById(R.id.text_exam_date);
            mMainLayout = itemView.findViewById(R.id.layout_main);

            // ClickListener for item.
            // Default method is override by the interface.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            // Call user defined onItemClick, which is implemented in parent class.
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
