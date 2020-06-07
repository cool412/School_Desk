package com.example.schooldesk.teacher.data;

public class AttendanceList {
    private String mStudentId, mStudentName, mAttendanceFlag;

    public AttendanceList() {
        this("99", "Default Name", "Present");
    }

    public AttendanceList(String mStudentId, String mStudentName, String mAttendanceFlag) {
        this.mStudentId = mStudentId;
        this.mStudentName = mStudentName;
        this.mAttendanceFlag = mAttendanceFlag;
    }

    public String getmStudentId() {
        return mStudentId;
    }

    public String getmStudentName() {
        return mStudentName;
    }

    public void setmStudentId(String mStudentId) {
        this.mStudentId = mStudentId;
    }

    public void setmStudentName(String mStudentName) {
        this.mStudentName = mStudentName;
    }

    public void setmAttendanceFlag(String mAttendanceFlag) {
        this.mAttendanceFlag = mAttendanceFlag;
    }

    public String getmAttendanceFlag() {
        return mAttendanceFlag;
    }
}
