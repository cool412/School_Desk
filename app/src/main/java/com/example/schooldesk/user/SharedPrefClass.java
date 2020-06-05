package com.example.schooldesk.user;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefClass {
    //private SharedPreferences sharedPreferences;
    private Context mContext;
    //@SuppressLint("StaticFieldLeak")
    private static SharedPrefClass mInstance;

    private static String KEY_SHARED_PREF_FILE_NAME = "com.example.preference_file";
    private static String KEY_PREF_USER_NAME = "key_pref_user_name";
    private static String KEY_PREF_LOGIN_STATUS = "key_pref_login_status";
    private static String KEY_PREF_SESSION_ID = "key_pref_session_key";
    private static String KEY_PERF_ACCOUNT_TYPE = "key_pref_account_type";
    private static String KEY_PREF_USER_FULL_NAME = "key_pref_user_full_name";
    private static String KEY_PREF_USER_ROLL_NUMBER = "key_pref_user_roll_number";
    private static String KEY_PREF_STUDENT_CLASS = "key_pref_student_standard";

    private SharedPrefClass(Context context) {
        this.mContext = context;
        //sharedPreferences = context.getSharedPreferences(KEY_SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized SharedPrefClass getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefClass(context);
        }
        return mInstance;
    }

    public void writeUserLogin(User user) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(KEY_SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_PREF_LOGIN_STATUS, user.getLoginStatus());
        editor.putString(KEY_PREF_USER_NAME, user.getUserName());
        editor.putString(KEY_PERF_ACCOUNT_TYPE, user.getUserRoll());
        editor.putString(KEY_PREF_SESSION_ID, user.getSessionId());
        editor.putString(KEY_PREF_USER_FULL_NAME, user.getFullName());
        editor.putString(KEY_PREF_USER_ROLL_NUMBER, user.getStudentRollNumber());
        editor.putString(KEY_PREF_STUDENT_CLASS, user.getStudentStandard());
        editor.apply();
    }

    public void userLogout(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(KEY_SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    // Write Login status to the shared preferences.
    // All below methods are for writing to SharedPreferences.
    public void writeLoginStatus(boolean status) {
        // Before writing we need to prepare editor for that.
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(KEY_SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(KEY_PREF_LOGIN_STATUS, status);
        // here we can also use editor.commit() but android studio suggested to use editor.apply();
        editor.apply();
    }

    public void writeUserName(String userName) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(KEY_SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_PREF_USER_NAME, userName);
        editor.apply();
    }

    public void writeSessionId(String sessionId) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(KEY_SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_PREF_SESSION_ID, sessionId);
        editor.apply();
    }

    public void writeAccountType(String accountType) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(KEY_SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_PERF_ACCOUNT_TYPE, accountType);
        editor.apply();
    }

    // All below methods are for reading the values stored in SharedPreferences.
    public boolean readLoginStatus() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(KEY_SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(KEY_PREF_LOGIN_STATUS, false);
    }

    public String readUserName() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(KEY_SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PREF_USER_NAME, "User");
    }

    public String readSessionId() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(KEY_SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PREF_SESSION_ID, "");
    }

    public String readAccountType() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(KEY_SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PERF_ACCOUNT_TYPE, "");
    }

    public String getUserFullName (){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(KEY_SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PREF_USER_FULL_NAME,"Default User Name");
    }

    public String getUserRollNumber() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(KEY_SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PREF_USER_ROLL_NUMBER,"00");
    }

    public String getStudentClass(){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(KEY_SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_PREF_STUDENT_CLASS,"AA");
    }
}
