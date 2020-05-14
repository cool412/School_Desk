package com.example.schooldesk.data;

public class ExamItems {
    private String mExamName, mExamDesc;
    private String[] mExamSubject;

    public ExamItems(String name, String desc, String[] sub) {
        mExamName = name;
        mExamDesc = desc;
        mExamSubject = sub;
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
}
