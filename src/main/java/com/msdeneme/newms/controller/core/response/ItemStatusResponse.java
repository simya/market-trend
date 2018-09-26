package com.msdeneme.newms.controller.core.response;

import com.msdeneme.newms.controller.core.BaseRestResponse;

public class ItemStatusResponse extends BaseRestResponse {

    private String equity;
    private String oper;
    private String value;
    private String valueLabel;
    private String date;
    private String dateLabel;
    private String trade;
    private String tradeLabel;

    public String getEquity() {
        return equity;
    }

    public void setEquity(String equity) {
        this.equity = equity;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public String getValueLabel() {
        return valueLabel;
    }

    public void setValueLabel(String valueLabel) {
        this.valueLabel = valueLabel;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDateLabel() {
        return dateLabel;
    }

    public void setDateLabel(String dateLabel) {
        this.dateLabel = dateLabel;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public String getTradeLabel() {
        return tradeLabel;
    }

    public void setTradeLabel(String tradeLabel) {
        this.tradeLabel = tradeLabel;
    }
}
