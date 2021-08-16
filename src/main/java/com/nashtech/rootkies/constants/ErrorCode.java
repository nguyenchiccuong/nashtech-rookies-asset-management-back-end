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
    public static final String ERR_ROLE_NOT_FOUND = "ERR_ROLE_NOT_FOUND";
    // Asset
    public static final String ASSET_NOT_FOUND = "ASSET_NOT_FOUND";
    public static final String ASSET_IS_DELETED = "ASSET_IS_DELETED";
    public static final String ASSET_ALREADY_ASSIGNED = "ASSET_ALREADY_ASSIGNED";
    public static final String NAME_IS_EMPTY = "NAME_IS_EMPTY";
    public static final String SPEC_IS_EMPTY = "SPEC_IS_EMPTY";
    public static final String DATE_INCORRECT_FORMAT = "DATE_INCORRECT_FORMAT";
    public static final String STATE_INCORRECT_FORMAT = "STATE_INCORRECT_FORMAT";
    public static final String ERR_EDIT_ASSET = "ERR_EDIT_ASSET";

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
    public static final String GET_USER_FAIL = "GET_USER_FAIL";
    public static final String ERR_CONVERT_ENTITY_DTO_FAIL = "ERR_CONVERT_ENTITY_DTO_FAIL";
    public static final String ERR_CREATE_USER_DOB = "ERR_CREATE_USER_DOB";
    public static final String ERR_CREATE_USER_JD = "ERR_CREATE_USER_JD";
    public static final String ERR_CREATE_USER_JD_DOB = "ERR_CREATE_USER_JD_DOB";
    public static final String ERR_GET_ALL_USER = "ERR_GET_ALL_USER";
    public static final String ERR_DISABLE_USER = "ERR_DISABLE_USER";
    public static final String ERR_UPDATE_USER_FAIL = "ERR_UPDATE_USER_FAIL";

    /** CONVERTER **/
    public static final String ERR_CONVERT_DTO_ENTITY_FAIL = "ERR_CONVERT_DTO_ENTITY_FAIL";

    /** AUTHENTICATION - AUTHORIZATION **/
    public static final String ERR_USER_LOGIN_FAIL = "ERR_USER_LOGIN_FAIL";

    /** ROLE **/
    public static final String ERR_GET_ALL_ROLE = "ERR_GET_ALL_ROLE";

    /** ASSET **/
    public static final String ERR_RETRIEVE_ASSET_FAIL = "ERR_RETRIEVE_ASSET_FAIL";
    public static final String ERR_COUNT_ASSET_FAIL = "ERR_RETRIEVE_ASSET_FAIL";
    public static final String ERR_ASSETCODE_NOT_FOUND = "ERR_ASSETCODE_NOT_FOUND";
    public static final String ERR_ASSET_STATE_NOT_CORRECT = "ERR_ASSET_STATE_NOT_CORRECT";
    public static final String ERR_CREATE_ASSET_FAIL = "ERR_CREATE_ASSET_FAIL";
    public static final String ERR_ASSET_ALREADY_HAVE_ASSIGNMENT = "ERR_ASSET_ALREADY_HAVE_ASSIGNMENT";
    public static final String ERR_ASSET_DELETE_FAIL = "ERR_ASSET_DELETE_FAIL";
    public static final String ERR_ASSET_NOT_AVAILABLE = "ERR_ASSET_NOT_AVAILABLE";

    /** LOCATION **/
    public static final String ERR_LOCATION_NOT_FAIL = "ERR_LOCATION_NOT_FAIL";
    public static final String ERR_CHECK_VALID_ASSIGNMENT = "ERR_CHECK_VALID_ASSIGNMENT";

    // ASSIGNMENT
    public static final String ERR_COUNT_ASSIGNMENT_FAIL = "ERR_COUNT_ASSIGNMENT_FAIL";
    public static final String ERR_RETRIEVE_ASSIGNMENT_FAIL = "ERR_RETRIEVE_ASSIGNMENT_FAIL";
    public static final String ERR_ASSIGNMENT_ID_NOT_FOUND = "ERR_ASSIGNMENT_ID_NOT_FOUND";
    public static final String ERR_ASSIGNMENT_DELETE_FAIL = "ERR_ASSIGNMENT_DELETE_FAIL";
    public static final String ERR_ASSIGNMENT_DELETE_FAIL_DUE_TO_STATE = "ERR_ASSIGNMENT_DELETE_FAIL_DUE_TO_STATE";
    public static final String ERR_ASSIGNMENT_ALREADY_ACCEPTED_OR_DECLINED = "ERR_ASSIGNMENT_ALREADY_ACCEPTED_OR_DECLINED";
    public static final String ERR_ASSIGNED_DATE_UPDATE_IS_EARLIER_THAN_CURRENT = "ERR_ASSIGNMENT_DATE_UPDATE_IS_EARLIER_THAN_CURRENT";
    public static final String ERR_ASSIGNMENT_UPDATE_FAIL = "ERR_ASSIGNMENT_UPDATE_FAIL";
    public static final String ERR_ASSIGNMENT_NOT_YOUR = "ERR_ASSIGNMENT_NOT_YOUR";
    public static final String ERR_ASSIGNMENT_ACCEPTED_FAIL = "ERR_ASSIGNMENT_ACCEPTED_FAIL";
    public static final String ERR_ASSIGNMENT_DECLINED_FAIL = "ERR_ASSIGNMENT_DECLINED_FAIL";



    // REQUEST
    public static final String ERR_REQUEST_NOT_FOUND = "ERR_REQUEST_NOT_FOUND";
    public static final String ERR_REQUEST_ALREADY_COMPLETE = "ERR_REQUEST_ALREADY_COMPLETE";
    public static final String ERR_REQUEST_CANCEL_FAIL = "ERR_REQUEST_CANCEL_FAIL";
    public static final String ERR_REQUEST_COMPLETE_FAIL = "ERR_REQUEST_COMPLETE_FAIL";

}
