package com.mzy.moban.model;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * Created by mengzy on 2015/8/6.
 */
public class App_user extends BaseModel{
    private String customer_id;
    private String app_account;
    private String app_secret;
    private String easemob_accout;
    private String easemob_secret;


    public String getApp_account() {
        return app_account;
    }

    public void setApp_account(String app_account) {
        this.app_account = app_account;
    }

    public String getApp_secret() {
        return app_secret;
    }

    public void setApp_secret(String app_secret) {
        this.app_secret = app_secret;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getEasemob_accout() {
        return easemob_accout;
    }

    public void setEasemob_accout(String easemob_accout) {
        this.easemob_accout = easemob_accout;
    }

    public String getEasemob_secret() {
        return easemob_secret;
    }

    public void setEasemob_secret(String easemob_secret) {
        this.easemob_secret = easemob_secret;
    }
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
