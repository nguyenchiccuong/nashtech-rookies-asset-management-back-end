package com.nashtech.rootkies.constants;

public  class ErrorCode {

    /** CATEGORY **/
    public static final String ERR_CATEGORY_NOT_FOUND   = "ERR_CATEGORY_NOT_FOUND";
    public static final String ERR_CATEGORY_EXISTED     = "ERR_CATEGORY_EXISTED";
    public static final String ERR_CREATE_CATEGORY_FAIL = "ERR_CREATE_CATEGORY_FAIL";
    public static final String ERR_UPDATE_CATEGORY_FAIL = "ERR_UPDATE_CATEGORY_FAIL";
    public static final String ERR_DELETE_CATEGORY_FAIL = "ERR_DELETE_CATEGORY_FAIL";

    /** PRODUCT **/
    public static final String ERR_CREATE_PRODUCT_FAIL = "ERR_CREATE_PRODUCT_FAIL";
    public static final String ERR_PRODUCT_NOT_FOUND   = "ERR_PRODUCT_NOT_FOUND";
    public static final String ERR_UPDATE_PRODUCT_FAIL = "ERR_UPDATE_PRODUCT_FAIL";

    /** CART **/
    public static final String ERR_CART_NOT_FOUND           = "ERR_CART_NOT_FOUND";
    public static final String ERR_ADD_ITEM_CART_FAIL       = "ERR_ADD_ITEM_CART_FAIL";
    public static final String ERR_DUPLICATE_ITEM_CART      = "ERR_DUPLICATE_ITEM_CART";
    public static final String ERR_UPDATE_CART_FAIL         = "ERR_UPDATE_CART_FAIL";
    public static final String ERR_REMOVE_ITEM_CART_FAIL    = "ERR_REMOVE_ITEM_CART_FAIL";
    public static final String ERR_ITEM_CART_NOT_FOUND      = "ERR_ITEM_CART_NOT_FOUND";

    /** USER **/
    public static final String ERR_USER_NOT_FOUND           = "ERR_USER_NOT_FOUND";
    public static final String ERR_USER_SIGNUP_FAIL         = "ERR_USER_SIGNUP_FAIL";
    public static final String ERR_USER_EXISTED             = "ERR_USER_EXISTED";
    public static final String ERR_CREATE_USER_FAIL         = "ERR_CREATE_USER_FAIL";


    /** CONVERTER **/
    public static final String ERR_CONVERT_DTO_ENTITY_FAIL  = "ERR_CONVERT_DTO_ENTITY_FAIL";

    /** AUTHENTICATION - AUTHORIZATION **/
    public static final String ERR_USER_LOGIN_FAIL          = "ERR_USER_LOGIN_FAIL";
}
