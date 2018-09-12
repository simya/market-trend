package com.msdeneme.newms.core.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by myalcinsoy on 11-Sep-18.
 */
public class Equity implements Serializable {

    private long id;
    private String name;
    private String status;
    private Date date;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
