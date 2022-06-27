package com.example.fon_classroommanagment.Configuration;

public class Routes {

    //classroom
    public  static final String  CLASSROOM_PREFIX="/classroom";
    public  static final String  CLASSROOM_FILTER="/filter";
    public  static final String  CLASSROOM_PAGING="/{page}";
    public  static final String  CLASSROOM_SEARCH="/{page}/search";
    public  static final String  CLASSROOM_DETAILS="";
    public  static final String  CLASSROOM_APPOITMENTS="/appointments";
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
    public  static final String USER_REQUESTED_APPOINTMENTS="/appointments-requested";

    public  static final String PASSWORD_RESET="/password/reset";
    public  static final String EMAIL_RESET="/email/reset";
    public  static final String IS_USER_ADMIN="/admin";


}
