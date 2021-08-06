package com.nashtech.rootkies.constants;

public class ErrorCode {

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

    /** USER **/
    public static final String ERR_USER_NOT_FOUND = "ERR_USER_NOT_FOUND";
    public static final String ERR_USER_SIGNUP_FAIL = "ERR_USER_SIGNUP_FAIL";
    public static final String ERR_USER_EXISTED = "ERR_USER_EXISTED";
    public static final String ERR_CREATE_USER_FAIL = "ERR_CREATE_USER_FAIL";

    /** CONVERTER **/
    public static final String ERR_CONVERT_DTO_ENTITY_FAIL = "ERR_CONVERT_DTO_ENTITY_FAIL";

    /** AUTHENTICATION - AUTHORIZATION **/
    public static final String ERR_USER_LOGIN_FAIL = "ERR_USER_LOGIN_FAIL";

    /** ASSET **/
    public static final String ERR_RETRIEVE_ASSET_FAIL = "ERR_RETRIEVE_ASSET_FAIL";
    public static final String ERR_COUNT_ASSET_FAIL = "ERR_RETRIEVE_ASSET_FAIL";
    public static final String ERR_ASSETCODE_NOT_FOUND = "ERR_ASSETCODE_NOT_FOUND";
}
