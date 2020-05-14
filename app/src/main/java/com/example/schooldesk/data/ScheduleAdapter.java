package com.example.schooldesk.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooldesk.R;

import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {

    private Context mContext;
    private ArrayList<scheduleItems> mExampleList;

    public ScheduleAdapter(Context context, ArrayList<scheduleItems> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.time_card_week, parent, false);
        return new ScheduleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        scheduleItems currentItem = mExampleList.get(position);

        //String imageUrl = currentItem.getImageUrl();
        //String creatorName = currentItem.getCreator();
        //int likeCount = currentItem.getLikeCount();
        String subject = currentItem.getSubject();
        String time = currentItem.getTime();
        String teacher = currentItem.getTeacher();

        holder.mSubjectView.setText(subject);
        holder.mTimeView.setText(time);
        holder.mTeacherView.setText(teacher);

        //holder.mTextViewCreator.setText(creatorName);
        //holder.mTextViewLikes.setText("Likes: " + likeCount);
        //Picasso.get().load(imageUrl).fit().centerInside().into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    static class ScheduleViewHolder extends RecyclerView.ViewHolder {
        //ImageView mImageView;
        //TextView mTextViewCreator;
        //TextView mTextViewLikes;

        TextView mSubjectView, mTimeView, mTeacherView;

        ScheduleViewHolder(@NonNull View itemView) {
            super(itemView);
            mSubjectView = itemView.findViewById(R.id.subject);
            //mImageView = itemView.findViewById(R.id.image_view);
            mTimeView = itemView.findViewById(R.id.text_view_time);
            mTeacherView = itemView.findViewById(R.id.text_view_likes);
        }
    }
}
