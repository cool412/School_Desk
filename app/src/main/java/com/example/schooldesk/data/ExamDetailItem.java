package com.example.schooldesk.data;

public class ExamDetailItem {
    private String mDayOfExam, mMonthOfExam, mTimeOfExam, mSubject, mTotalMarks, mObtainedMarks, mExamDescription, mResult;

    public ExamDetailItem(String subject, String day, String month, String time, String total, String obtainedMarks, String description, String result) {
        mSubject = subject;
        mDayOfExam = day;
        mMonthOfExam = month;
        mTimeOfExam = time;
        mTotalMarks = total;
        mObtainedMarks = obtainedMarks;
        mExamDescription = description;
        mResult = result;
    }

    public String getSubject() {
        return mSubject;
    }

    public String getDateOfExam() {
       // return mDateOfExam;
        return "";
    }


    public String getDayOfExam() {
        return mDayOfExam;
    }

    public String getMonthOfExam() {
        return mMonthOfExam;
    }

    public String getTimeOfExam() {
        return mTimeOfExam;
    }

    public String getTotalMarks() {
        return "Total Makes: "+mTotalMarks;
    }

    public String getObtainedMarks() {
        return "Marks Obtained: "+mObtainedMarks;
    }

    public String getMarks(){
        return "Marks: " + mObtainedMarks + " / " + mTotalMarks;
    }

    public String getExamDescription() {
        return mExamDescription;
    }

    public String getResult(){
        return mResult;
    }

}
