package com.mzy.moban.exception;


public class App_userException extends Exception {
    private String errCode;
    private String[] parameters;
    public App_userException(String errCode, String... parameters) {
        super(errCode);
        this.errCode = errCode;
        this.parameters = parameters;
    }

    public App_userException(String msg, Throwable clause) {
        super(msg, clause);
    }

    public App_userException(String msg) {
        super(msg);
    }

    public App_userException(Throwable clause) {
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
