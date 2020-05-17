package com.example.schooldesk.data;

import java.io.Serializable;

public class ExamItems implements Serializable {
    private String mExamName, mExamDesc, mClassNumber, mExamStartDay, mExamStartMonth, mExamEndDay, mExamEndMonth;
    private String[] mExamSubject;

    public ExamItems(String name, String desc, String[] sub, String classNumber, String examStartDay,
                     String examStartMonth, String examEndDay, String examEndMonth) {
        mExamName = name;
        mExamDesc = desc;
        mExamSubject = sub;
        mClassNumber = classNumber;
        mExamStartDay = examStartDay;
        mExamStartMonth = examStartMonth;
        mExamEndDay = examEndDay;
        mExamEndMonth = examEndMonth;
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
        return mExamStartDay;
    }

    public String getExamStartMonth() {
        return mExamStartMonth;
    }

    public String getExamEndDay() {
        return mExamEndDay;
    }

    public String getmExamEndMonth() {
        return mExamEndMonth;
    }

    public String getExamDateRange() {
        String datePart = "Date: " + mExamStartDay + " " + mExamStartMonth + " ";
        if (mExamEndDay.isEmpty() | mExamEndMonth.isEmpty()) {
            return datePart;
        } else {
            return datePart + " to " + mExamEndDay + " " + mExamEndMonth;
        }
    }
}
