package com.readystatesoftware.chuck.internal.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import nl.qbusict.cupboard.annotation.Index;

/**
 * Created by zhanghao.
 * 2018/3/29-15:37
 */

public class PushTransaction {

    private static final SimpleDateFormat TIME_ONLY_FMT = new SimpleDateFormat("HH:mm:ss", Locale.US);

    public static final String[] PARTIAL_PROJECTION = new String[] {
            "_id",
            "code",
            "serviceId",
            "responseDate",
            "account",
            "content",
            "isRead"
    };

    private Long _id;
    private String content;
    private boolean isRead = false;
    private String code;
    private String account;
    private String serviceId;
    @Index
    private Date responseDate;

    public String getRequestStartTimeString() {
        return (responseDate != null) ? TIME_ONLY_FMT.format(responseDate) : null;
    }

    public Long getId() {
        return _id;
    }

    public void setId(Long _id) {
        this._id = _id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean read) {
        isRead = read;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }
}
