package com.example.fon_classroommanagment.Configuration;

public class Routes {

    //auth

    public  static final String REGISTER ="/register";
    public  static final String REGISTER_CONFIRM ="/register/{token}";

    public static  final String LOGIN="/login";
    public static  final String LOGOUT="/logout";



    //appointment
    public  static final String APPOINTMENT_PREFIX ="/appointment";


    public  static final String APPOINTMENT_DELETE ="";
    public  static final String APPOINTMENT_CLASSROOM ="/appointments";
    public  static final String APPOINTMENTS ="";
    public  static final String APPOINTMENT_CONFIRM ="/confirm";
    public  static final String APPOINTMENT_CONFIRM_ALL ="/confirm/all";
    public  static final String APPOINTMENT_RESERVE ="/reserve";
    public  static final String APPOINTMENT_SEARCH ="/search";
    public  static final String APPOINTMENT_DATE ="/{date}";
    public  static final String APPOINTMENT_AVAILABILITY ="/available";
    public  static final String APPOINTMENT_UPDATE ="";



    //classroom
    public  static final String  CLASSROOM_PREFIX="/classroom";
    public  static final String  CLASSROOM_FILTER="/filter";
    public  static final String  CLASSROOM_PAGING="/{page}";
    public  static final String  CLASSROOM_SEARCH="/{page}/search";
    public  static final String  CLASSROOM_DETAILS="";
    public  static final String CLASSROOM_PAGING_PARTIAL_INFO ="/main-info/{page}";
    public  static final String CLASSROOM_PARTIAL_INFO ="/main-info";


    //common
    public  static final String  COMMON_PREFIX="/common";
    public  static final String  COMMON_ALL_EMPLOYEE_TYPES="/employee/types";
    public  static final String  COMMON_ALL_EDUCATION_TITLES="/education/titles";
    public  static final String  COMMON_ALL_EMPLOYEE_DEPARTMENTS="/employee/departments";
    public  static final String  COMMON_ALL_CLASSROOM_TYPES="/classroom/types";
    public  static final String  COMMON_ALL_APPOINTMENT_TYPES="/appointment/types";



    //user
    public  static final String  USER_PREFIX="/user";
    public  static final String USER_DETAILS="";
    public  static final String USER_APPOINTMENTS="/appointments";
    public  static final String USER_APPOINTMENTS_PENDING ="/appointments/pending";
    public  static final String USER_REQUESTED_APPOINTMENTS="/appointments-requested";

    public  static final String PASSWORD_RESET="/password/reset";
    public  static final String EMAIL_RESET="/email/reset";
    public  static final String IS_USER_ADMIN="/admin";


}
