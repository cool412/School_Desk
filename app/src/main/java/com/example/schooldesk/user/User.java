package com.example.schooldesk.user;

public class User {
    private String sessionId, userName, userRoll;
    private boolean loginStatus;

    public User (boolean status, String user, String roll, String session){
        this.loginStatus = status;
        this.userName = user;
        this.userRoll = roll;
        this.sessionId = session;
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
}
