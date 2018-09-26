package com.msdeneme.newms.utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class MsUtil {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(MsUtil.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
    private static final SecureRandom random = new SecureRandom();
    private static final Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
    private static       String hostIp;
    private static Long sequence = 0L ;

    /**
     * @param list
     * @return boolean
     * */
    public static <T> boolean isNullOrEmptyList(List<T> list) {
        return CollectionUtils.isEmpty(list);
    }

    /**
     * @param value
     * @return boolean
     * */
    public static boolean isNullOrEmptyString(String value) {
        return (value == null || value.equalsIgnoreCase(""));
    }

    /**
     * year month day hour second millisecond 2 digit random number
     * @return String
     * */

    /**
     * @param apiKey
     * @param request
     * @return HttpEntity
     * */
    public static HttpEntity<String> getEntityForRestTemplate(Object request, String apiKey) {
        HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("application", "json", Charset.forName("UTF-8"));
        headers.setContentType(mediaType);
        headers.set("Authorization", "key= " + apiKey);
        String requestJson = gson.toJson(request);
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        return entity;
    }


    public static String getJsonString(Object obj){
        return gson.toJson(obj);
    }



    public static Date addToNow(int field, int value) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(field, value);
        return calendar.getTime();
    }

    /**
     * @param dateToConvert
     * @return LocalDateTime
     * */
    public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * @return Host Ip String
     * */
    public static String getHostIp() {
        if(isNullOrEmptyString(hostIp)) {
            logger.debug("host ip is null/empty, trying to gather it");
            try {
                hostIp = "";
                Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
                while (interfaces.hasMoreElements()) {
                    NetworkInterface current = interfaces.nextElement();
                    if (!current.isUp() || current.isLoopback() || current.isVirtual()) {
                        continue;
                    }

                    Enumeration<InetAddress> addresses = current.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        InetAddress currentAddress = addresses.nextElement();
                        if (currentAddress.isLoopbackAddress()) {
                            continue;
                        }

                        if (currentAddress instanceof Inet4Address) {
                            hostIp = currentAddress.getHostAddress();
                            //logger.debug("found value for host as {}",hostIp);
                            break;
                        }
                    }
                    if(!isNullOrEmptyString(hostIp)) {
                        break;
                    }
                }
            } catch (SocketException e) {
                logger.error("exception thrown when setting host ip : {}", e.getLocalizedMessage());
            }
        } else {
            logger.info("using already found value as host id {}",hostIp);
        }
        return hostIp;
    }

    public static int getSignValue(){
        LocalDateTime now = LocalDateTime.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();

        int value = year + month + day + hour + minute;

        return value;
    }

    public static String getBuyLabel(String locale){
        if ( locale == null || "en".equals(locale))
            return "BUY :";

        if ( locale.equals("es") || "ca".equals(locale)){
            return "COMPRAR : ";
        }
        else if ( locale.equals("ja")){
            return "購入 : ";
        }
        else if ( locale.equals("de")){
            return "KAUFEN : ";
        }
        else if ( locale.equals("it")){
            return "ACQUISTARE : ";
        }
        else if ( locale.equals("fr")){
            return "ACHETEN : ";
        }
        else if ( locale.equals("ko")) {
            return "사다 : ";
        }
        else if ( locale.equals("zh")) {
            return "购买 : ";
        }
        else if ( locale.equals("tr")) {
            return "AL : ";
        }

        return "BUY : ";
    }

    public static String getSellLabel(String locale) {
        if (locale == null || "en".equals(locale))
            return "SELL :";

        if (locale.equals("es") || "ca".equals(locale)) {
            return "VENDER : ";
        } else if (locale.equals("ja")) {
            return "売る : ";
        } else if (locale.equals("de")) {
            return "VERKAUFEN : ";
        }
        else if ( locale.equals("it")){
            return "VENDERE : ";
        }
        else if (locale.equals("fr")) {
            return "VENDRE : ";
        } else if (locale.equals("ko")) {
            return "팔다 : ";
        } else if (locale.equals("zh")) {
            return "卖 : ";
        } else if (locale.equals("tr")) {
            return "SAT : ";
        }

        return "SELL : ";
    }

    public static String getDateLabel(String locale){
        if ( locale == null || "en".equals(locale))
            return "DATE :";

        if ( locale.equals("es")|| "ca".equals(locale)){
            return "FECHA : ";
        }
        else if ( locale.equals("ja")){
            return "日付 : ";
        }
        else if ( locale.equals("de")){
            return "DATUM : ";
        }
        else if ( locale.equals("it")){
            return "DATA : ";
        }
        else if ( locale.equals("fr")){
            return "DATE : ";
        }
        else if ( locale.equals("ko")) {
            return "날짜 : ";
        }
        else if ( locale.equals("zh")) {
            return "日期 : ";
        }
        else if ( locale.equals("tr")) {
            return "TARİH : ";
        }

        return "DATE : ";
    }

    public static String getTradeLabel(String locale) {
        if (locale == null || "en".equals(locale))
            return "LAST TRANSACTION :";

        if (locale.equals("es") || "ca".equals(locale)) {
            return "ÚLTIMO TRANSACCIÓN : ";
        } else if (locale.equals("ja")) {
            return "最後の取引 : ";
        } else if (locale.equals("de")) {
            return "LETZTE TRANSAKTION";
        } else if ( locale.equals("it")){
            return "ULTIMA TRANSAZIONE : ";
        } else if (locale.equals("fr")) {
            return "DERNIÈRE TRANSACTION : ";
        } else if (locale.equals("ko")) {
            return "마지막 거래 : ";
        } else if (locale.equals("zh")) {
            return "最后的交易 : ";
        } else if (locale.equals("tr")) {
            return "SON İŞLEM : ";
        }

        return "LAST TRANSACTION : ";
    }

}


