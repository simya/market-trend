package com.msdeneme.newms.utility;

public class MsQueries {

    public static final String VALIDATION_QUERY = "SELECT 1";

    //region Push App Param
    public static final String GET_ALL_PUSH_APP_PARAM = "SELECT * FROM APP_PARAM";
    //endregion

    //region Push Service Log
    public static final String INSERT_APP_SERVICE_LOG = "INSERT INTO APP_SERVICE_LOG (SERVICENAME, REQUEST, RESPONSE, IP, USERAGENT, CREATED_DATE) " +
                                                        "VALUES (?, ?, ?, ?, ?, ?)";


}
