package com.msdeneme.newms.core.enums;

import javax.xml.bind.annotation.XmlEnum;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Created by Akif Hatipoglu on 24.01.2018.
 */
@XmlEnum
public enum ReturnCode implements Serializable {

    SUCCESS(0, "Success"),
    FAILED(-1, "Failed");


    private int code;
    private String message;

    ReturnCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ReturnCode get(int code) {
        return Arrays.stream(values()).filter(x -> x.getCode() == code).findAny().orElse(ReturnCode.FAILED);
    }
}
