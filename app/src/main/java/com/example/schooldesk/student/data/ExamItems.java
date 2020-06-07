package com.example.schooldesk.student.data;

import java.io.Serializable;

public class ExamItems implements Serializable {
    private String mExamName, mExamDesc, mClassNumber, mExamStartDate, mExamEndDate, mExamId;
    private String[] mExamSubject;

    public ExamItems(String id, String name, String desc, String[] sub, String classNumber,
                     String examStartDate, String examEndDate) {
        mExamId = id;
        mExamName = name;
        mExamDesc = desc;
        mExamSubject = sub;
        mClassNumber = classNumber;
        mExamStartDate = examStartDate;
        //mExamStartMonth = examStartMonth;
        mExamEndDate = examEndDate;
        //mExamEndMonth = examEndMonth;
    }

    public String getExamName() {
        return mExamName;
    }

    public String getExamDesc() {
        return mExamDesc;
    }

    public String[] getExamSubject() {
        return mExamSubject;
    }

    public String getClassNumber() {
        return "Class: " + mClassNumber;
    }

    public String getExamStartDay() {
        return mExamStartDate;
    }

    //public String getExamStartMonth() {
    //    return mExamStartMonth;
    //}

    public String getExamEndDay() {
        return mExamEndDate;
    }

    //public String getmExamEndMonth() {
    //    return mExamEndMonth;
    //}

    public String getExamDateRange() {
        String datePart = "Date: " + mExamStartDate + " ";
        if (mExamEndDate.isEmpty()) {
            return datePart;
        } else {
            return datePart + " to " + mExamEndDate;
        }
    }

    public String getExamId() {
        return mExamId;
    }
}
