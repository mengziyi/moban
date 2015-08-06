package com.mzy.moban.exception;


public class ServiceException extends Exception {
    private String errCode;
    private String[] parameters;
    public ServiceException(String errCode, String... parameters) {
        super(errCode);
        this.errCode = errCode;
        this.parameters = parameters;
    }

    public ServiceException(String msg, Throwable clause) {
        super(msg, clause);
    }

    public ServiceException(String msg) {
        super(msg);
    }

    public ServiceException(Throwable clause) {
        super(clause);
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String[] getParameters() {
        return parameters;
    }

    public void setParameters(String[] parameters) {
        this.parameters = parameters;
    }
}
