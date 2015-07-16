package com.mzy.moban.action;

import com.mzy.moban.model.App;
import com.mzy.moban.service.AppService;
import com.mzy.moban.util.FormatUtils;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by mengzy on 2015/7/16.
 */
public class AppAction extends ActionSupport{
    private static Log log = LogFactory.getLog(AppAction.class);
    @Autowired
    public AppService appService;
    public List<App> apps;

    @Override
    public String execute() throws Exception {
        List<App> m=appService.getAllApps();
        System.out.println(m);
        System.out.println("aaa");
        return "aaa";

    }

    public String userStatistics() throws Exception {
        apps=appService.getAllApps();
        System.out.println(apps);
        System.out.println("ccvvvvvchhhaaacccccc");
        log.info(apps);
        return "bbb";

    }

    public static void main(String[] args) throws ParseException {
        String a="1990-02-28";
        Date cc= FormatUtils.parseDate(a);
        System.out.println(cc);
    }

    public List<App> getApps() {
        return apps;
    }

    public void setApps(List<App> apps) {
        this.apps = apps;
    }
}
