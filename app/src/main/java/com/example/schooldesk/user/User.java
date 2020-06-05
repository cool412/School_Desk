package com.example.schooldesk.user;

public class User {
    private String sessionId, userName, userRoll, fullName, studentRollNumber, studentStandard;
    private boolean loginStatus;

    public User (boolean status, String user, String roll, String session, String name, String rollNo, String standard){
        this.loginStatus = status;
        this.userName = user;
        this.userRoll = roll;
        this.sessionId = session;
        this.fullName = name;
        this.studentRollNumber = rollNo;
        this.studentStandard = standard;
    }
    public boolean getLoginStatus(){
        return loginStatus;
    }
    public String getUserName(){
        return userName;
    }
    public String getUserRoll(){
        return userRoll;
    }
    public String getSessionId(){
        return sessionId;
    }
    public String getFullName(){
        return fullName;
    }
    public String getStudentRollNumber(){
        return studentRollNumber;
    }
    public String getStudentStandard(){
        return studentStandard;
    }
}
