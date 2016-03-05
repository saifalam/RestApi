package com.alam.saif.messenger.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by saif on 3/5/16.
 */
@XmlRootElement
public class ErrorMessage {
    private String errorMessage;
    private int errorCode;

    public ErrorMessage() {
    }

    public ErrorMessage(String errorMessage, int errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
}
