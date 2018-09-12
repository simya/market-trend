package com.msdeneme.newms.utility;

/**
 * Created by Akif Hatipoglu on 26.01.2018.
 */
public class Constants {

    public static final int LOG_MAX_SIZE = 2000;

    public static final String PROD_ENVIRONMENT = "PROD";
    public static final String TEST_ENVIRONMENT = "TEST";

    public static final String ENVIRONMENT = "environment";
    public static final String PROPERTY_UPDATE_INTERVAL = "PROPERTY_UPDATE_INTERVAL";
    public static final String PROPERTY_UPDATE_INTERVAL_TIME = "3600000";

    public static final String SQL_QUERY_FOR_AND = " AND ";
    public static final String SQL_QUERY_FOR_OR = " OR ";
    public static final String SQL_QUERY_FOR_IN = " IN (";
    public static final String SQL_QUERY_FOR_EQUAL_MARK = " = ? ";
    public static final String SQL_QUERY_FOR_BETWEEN = " BETWEEN ";

    public static final String PUSH_TYPE_COLUMN_NAME = "PUSH_TYPE_ID";
    public static final String PUSHSUB_TYPE_COLUMN_NAME = "PUSH_SUBTYPE_ID";
    public static final String DEVICE_TYPE_COLUMN_NAME = "DEVICETYPE";
    public static final String PUSH_TOKEN = "TOKEN";
    public static final String CREATED_DATE= "CREATED_DATE";
    public static final String SQL_QUERY_FOR_ORDER = "ORDER BY ID DESC";

    public static void main(String[] args) {
        System.out.print("Ms Deneme  Application up and running");
    }

}
