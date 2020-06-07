package com.example.schooldesk.student.data;

public class scheduleItems {
    private String mTime;
    private String mSubject;
    private String mTeacher;

    public scheduleItems(String time, String subject, String teacher){
        mTime = time;
        mSubject = subject;
        mTeacher = teacher;
    }
    public String getTime(){return mTime;}
    public String getSubject() {return mSubject;}
    public String getTeacher(){return mTeacher;}
}
