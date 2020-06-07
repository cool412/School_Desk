package com.example.schooldesk.student.data;

public class ExamDetailItem {
    private String mDayOfExam, mMonthOfExam, mYearOfExam, mSubject, mTotalMarks,
            mObtainedMarks, mPassingMarks, mExamStartTime, mExamEndTime, mResult;

    public ExamDetailItem(String subject, String day, String month, String year, String total, String obtainedMarks,
                          String passingMarks, String examStartTime, String examEndTime, String result) {
        mSubject = subject;
        mDayOfExam = day;
        mMonthOfExam = month;
        mYearOfExam = year;
        mTotalMarks = total;
        mObtainedMarks = obtainedMarks;
        mPassingMarks = passingMarks;
        mExamStartTime = examStartTime;
        mExamEndTime = examEndTime;
        mResult = result;
    }

    public String getSubject() {
        return mSubject;
    }

    public String getDateOfExam() {
        return "";
    }


    public String getDayOfExam() {
        return mDayOfExam;
    }

    public String getMonthOfExam() {
        return mMonthOfExam;
    }

    public String getYearOfExam() {
        return mYearOfExam;
    }

    public String getTotalMarks() {
        return "Total Marks: "+mTotalMarks;
    }

    public String getObtainedMarks() {
        return "Marks Obtained: "+mObtainedMarks;
    }

    public String getMarks(){
        return "Marks: " + mObtainedMarks + " / " + mTotalMarks;
    }

    public String getPassingMarks() {
        return "Passing Marks: " + mPassingMarks;
    }

    public String getExamDuration() {
        return mExamStartTime + " to " + mExamEndTime;
    }

    public String getResult(){
        return mResult;
    }

}
