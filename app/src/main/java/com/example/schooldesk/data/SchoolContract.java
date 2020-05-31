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
    public static String EXAM_LIST_KEY = "ExamList";
    public static String EXAM_ID_KEY = "ExamId";
    public static String EXAM_NAME_KEY = "ExamName";
    public static String EXAM_DESCRIPTION_KEY = "ExamDescription";
    public static String EXAM_START_DATE_KEY = "StartDate";
    public static String EXAM_END_DATE_KEY = "EndDate";
    public static String EXAM_SUBJECT_KEY = "Subjects";
    public static String EXAM_SUBJECT_NAME_KEY = "SubjectName";
    public static String TOTAL_SCORE_KEY = "TotalScored";
    public static String SUBJECT_APPEARED_KEY = "SubjectsAppeared";
    public static String OVERALL_RESULT_KRY = "OverallResult";
    public static String PERCENTAGE_SCORE_KEY = "Percentage";
    public static String SUBJECT_WISE_LIST_KEY = "SubjectWiseList";
    public static String SUBJECT_NAME_KEY = "SubjectName";
    public static String SUBJECT_RESULT_KEY = "Result";
    public static String SUBJECT_TOTAL_MARKS = "TotalMarks";
    public static String SUBJECT_PASSING_MARKS = "PassingMarks";
    public static String SUBJECT_OBTAINED_MARKS = "ObtainedMarks";
    public static String SUBJECT_DAY_OF_MONTH = "DayOfMonth";
    public static String SUBJECT_MONTH = "Month";
    public static String SUBJECT_YEAR = "Year";
    public static String SUBJECT_START_TIME = "StartTime";
    public static String SUBJECT_END_TIME = "EndTime";

    // Standard responses from server.
    public static String STUDENT_ROLL = "S";
    public static String TEACHER_ROLL = "T";
    public static String RESPONSE_SUCCESS = "Success";
    public static String RESPONSE_INCORRECT = "Incorrect";
    public static String RESPONSE_CORRECT = "Correct";
    public static String AUTH_RESPONSE_INVALID = "Invalid";
    public static String AUTH_RESPONSE_INACTIVE = "Inactive ";
    public static String SUBJECT_RESULT_PASS_RESPONSE = "Pass";
    public static String SUBJECT_RESULT_FAIL_RESPONSE = "Fail";
    public static String SUBJECT_RESULT_NOT_DECLARE_RESPONSE = "Not Declared";
    public static String SUBJECT_RESULT_EXAM_PENDING_RESPONSE = "Exam Pending";



    // URL constants.
    private static String BASE_URL = "http://edu.hydrocarbonz.com/index.php/interfaceclass/";
    public static String LOGIN_URL = BASE_URL + "getTok/";
    public static String SESSION_CHECK_URL = BASE_URL + "check_if_tok_correct/";
    public static String TIMETABLE_URL = BASE_URL + "getTimetableData/";
    public static String EXAM_URL = BASE_URL + "getExamScheduleForStudent/";
    public static String EXAM_DETAIL_URL = BASE_URL + "getExamDetailsForStudent";
}
