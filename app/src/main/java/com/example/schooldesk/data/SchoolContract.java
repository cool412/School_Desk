package com.example.schooldesk.data;

public class SchoolContract {
    // String constants.
    public static String DAY_MONDAY = "Monday";
    public static String DAY_TUESDAY = "Tuesday";
    public static String DAY_WEDNESDAY = "Wednesday";
    public static String DAY_THURSDAY = "Thursday";
    public static String DAY_FRIDAY = "Friday";
    public static String DAY_SATURDAY = "Saturday";

    // Key constants for HTTP request.
    public static String USERNAME_KEY = "Uname";
    public static String PASSWORD_KEY = "Pss";
    public static String USER_ROLL_KEY = "Isstudentteacher";
    public static String RESPONSE_KEY = "Response";
    public static String SUBJECT_KEY = "subject";
    public static String TIME_KEY = "time";
    public static String STANDARD_KEY = "standard";
    public static String TEACHER_KEY = "teacher";
    public static String AUTH_RESPONSE_KEY = "Authresp";
    public static String SESSION_KEY = "Sskey";
    public static String SESSION_SEND_KEY = "Tok";

    // Standard responses from server.
    public static String STUDENT_ROLL = "S";
    public static String TEACHER_ROLL = "T";
    public static String RESPONSE_SUCCESS = "Success";
    public static String RESPONSE_INCORRECT = "Incorrect";
    public static String RESPONSE_CORRECT = "Correct";
    public static String AUTH_RESPONSE_INVALID = "Invalid";
    public static String AUTH_RESPONSE_INACTIVE = "Inactive ";



    // URL constants.
    private static String BASE_URL = "http://edu.hydrocarbonz.com/index.php/interfaceclass/";
    public static String LOGIN_URL = BASE_URL + "getTok/";
    public static String SESSION_CHECK_URL = BASE_URL + "check_if_tok_correct/";
    public static String TIMETABLE_URL = BASE_URL + "getTimetableData/";
}
