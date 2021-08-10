package com.nashtech.rootkies.constants;

public class ErrorCode {

    // User
    public static final String SAME_PASSWORD = "SAME_PASSWORD";
    public static final String PASSWORD_NOT_CORRECT = "PASSWORD_NOT_CORRECT";
    public static final String PASSWORD_IS_EMPTY = "PASSWORD_IS_EMPTY";
    public static final String USER_IS_DISABLED = "USER_IS_DISABLED";
    public static final String USER_NOT_FOUND = "USER_NOT_FOUND";
    public static final String ERR_CHANGE_PASSWORD = "ERR_CHANGE_PASSWORD";
    public static final String USER_BLOCKED = "USER_BLOCKED";

    /** CATEGORY **/
    public static final String ERR_CATEGORY_NOT_FOUND = "ERR_CATEGORY_NOT_FOUND";
    public static final String ERR_CATEGORY_EXISTED = "ERR_CATEGORY_EXISTED";
    public static final String ERR_CREATE_CATEGORY_FAIL = "ERR_CREATE_CATEGORY_FAIL";
    public static final String ERR_UPDATE_CATEGORY_FAIL = "ERR_UPDATE_CATEGORY_FAIL";
    public static final String ERR_DELETE_CATEGORY_FAIL = "ERR_DELETE_CATEGORY_FAIL";
    public static final String ERR_HAVE_SUB_CATEGORIES = "ERR_HAVE_SUB_CATEGORIES";
    public static final String ERR_PARENT_CATEGORY_NOT_EXISTS = "ERR_PARENT_CATEGORY_NOT_EXISTS";
    public static final String ERR_CATEGORY_IDS_NOT_CORRECT = "ERR_CATEGORY_IDS_NOT_CORRECT";
    public static final String ERR_RETRIEVE_CATEGORY_FAIL = "ERR_RETRIEVE_CATEGORY_FAIL";
    public static final String ERR_CATEGORY_NAME_EXISTED = "ERR_CATEGORY_NAME_EXISTED";
    public static final String ERR_CATEGORY_CODE_EXISTED = "ERR_CATEGORY_CODE_EXISTED";

    /** USER **/
    public static final String ERR_USER_NOT_FOUND = "ERR_USER_NOT_FOUND";
    public static final String ERR_USER_SIGNUP_FAIL = "ERR_USER_SIGNUP_FAIL";
    public static final String ERR_USER_EXISTED = "ERR_USER_EXISTED";
    public static final String ERR_CREATE_USER_FAIL = "ERR_CREATE_USER_FAIL";
    public static final String ERR_CREATE_USER_DOB = "ERR_CREATE_USER_DOB";
    public static final String ERR_CREATE_USER_JD = "ERR_CREATE_USER_JD";
    public static final String ERR_CREATE_USER_JD_DOB = "ERR_CREATE_USER_JD_DOB";

    /** CONVERTER **/
    public static final String ERR_CONVERT_DTO_ENTITY_FAIL = "ERR_CONVERT_DTO_ENTITY_FAIL";

    /** AUTHENTICATION - AUTHORIZATION **/
    public static final String ERR_USER_LOGIN_FAIL = "ERR_USER_LOGIN_FAIL";

    /** ASSET **/
    public static final String ERR_RETRIEVE_ASSET_FAIL = "ERR_RETRIEVE_ASSET_FAIL";
    public static final String ERR_COUNT_ASSET_FAIL = "ERR_RETRIEVE_ASSET_FAIL";
    public static final String ERR_ASSETCODE_NOT_FOUND = "ERR_ASSETCODE_NOT_FOUND";
    public static final String ERR_ASSET_STATE_NOT_CORRECT = "ERR_ASSET_STATE_NOT_CORRECT";
    public static final String ERR_CREATE_ASSET_FAIL = "ERR_CREATE_ASSET_FAIL";
    public static final String ERR_ASSET_ALREADY_HAVE_ASSIGNMENT = "ERR_ASSET_ALREADY_HAVE_ASSIGNMENT";
    public static final String ERR_ASSET_DELETE_FAIL = "ERR_ASSET_DELETE_FAIL";

    /** LOCATION **/
    public static final String ERR_LOCATION_NOT_FAIL = "ERR_LOCATION_NOT_FAIL";

}
