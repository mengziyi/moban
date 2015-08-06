package com.mzy.moban.action;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * <title></title>
 * <p></p>
 * Copyright © 2013 Phoenix New Media Limited All Rights Reserved.
 *
 * @author zhuwei
 *         11/24/13
 */
public abstract class InterfAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

    private static final Logger log = Logger.getLogger(InterfAction.class);
    public static final String RESULT = "result";
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected  boolean success = true;
    protected  String msg;
    protected Map<String,Object> dataModel = new HashMap<String,Object>();

    protected void setResult(boolean success, String message) {
        this.success = success;
        this.msg = message;
    }

    @Override
    public void setServletRequest(HttpServletRequest httpServletRequest) {
        this.request = httpServletRequest;
    }

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    public String getResult() {
        try {
            dataModel.put("success", success);
            dataModel.put("msg", msg);
            Iterator<Map.Entry<String, Object>> iterator = dataModel.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                if (entry.getValue() == null) {
                    iterator.remove();
                }
            }
            JSONObject jsonObject = new JSONObject(dataModel);
            return jsonObject.toString();
        } catch (Exception e) {
            log.error(e);
            return "";
        }
    }

}
