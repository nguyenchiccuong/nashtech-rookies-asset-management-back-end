package com.nashtech.rootkies.constants;

public  class ErrorCode {

    /** CATEGORY **/
    public static final String ERR_CATEGORY_NOT_FOUND   = "ERR_CATEGORY_NOT_FOUND";
    public static final String ERR_CATEGORY_EXISTED     = "ERR_CATEGORY_EXISTED";
    public static final String ERR_CREATE_CATEGORY_FAIL = "ERR_CREATE_CATEGORY_FAIL";
    public static final String ERR_UPDATE_CATEGORY_FAIL = "ERR_UPDATE_CATEGORY_FAIL";
    public static final String ERR_DELETE_CATEGORY_FAIL = "ERR_DELETE_CATEGORY_FAIL";
    public static final String ERR_HAVE_SUB_CATEGORIES  = "ERR_HAVE_SUB_CATEGORIES";
    public static final String ERR_PARENT_CATEGORY_NOT_EXISTS  = "ERR_PARENT_CATEGORY_NOT_EXISTS";
    public static final String ERR_CATEGORY_IDS_NOT_CORRECT   = "ERR_CATEGORY_IDS_NOT_CORRECT";

    /** PRODUCT **/
    public static final String ERR_CREATE_PRODUCT_FAIL = "ERR_CREATE_PRODUCT_FAIL";
    public static final String ERR_PRODUCT_NOT_FOUND   = "ERR_PRODUCT_NOT_FOUND";
    public static final String ERR_UPDATE_PRODUCT_FAIL = "ERR_UPDATE_PRODUCT_FAIL";
    public static final String ERR_PRODUCT_OUT_OF_STOCK = "ERR_PRODUCT_OUT_OF_STOCK";
    public static final String ERR_PRODUCT_INVALID_QUANTITY = "ERR_PRODUCT_INVALID_QUANTITY";
    public static final String ERR_PRODUCT_INVALID_ORIGINAL_PRICE   = "ERR_PRODUCT_INVALID_ORIGINAL_PRICE";
    public static final String ERR_PRODUCT_INVALID_CATEGORY = "ERR_PRODUCT_INVALID_CATEGORY";

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

    //** BRAND **/
    public static final String ERR_BRAND_NOT_FOUND          = "ERR_BRAND_NOT_FOUND";
    public static final String ERR_CREATE_BRAND_FAIL        = "ERR_CREATE_BRAND_FAIL";
    public static final String ERR_BRAND_NAME_EMPTY         = "ERR_BRAND_NAME_EMPTY";
    public static final String ERR_BRAND_EXISTED            = "ERR_BRAND_EXISTED";

    /** ORGANIZATION **/
    public static final String ERR_ORGANIZATION_EXISTED     = "ERR_ORGANIZATION_EXISTED";
    public static final String CREATE_ORGANIZATION_FAIL     = "CREATE_ORGANIZATION_FAIL";
    public static final String ERR_ORGANIZATION_NOT_FOUND   = "ERR_ORGANIZATION_NOT_FOUND";
}
