package com.mzy.moban.action;

import com.mzy.moban.model.App;
import com.mzy.moban.service.AppService;
import com.opensymphony.xwork2.ActionSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mengzy on 2015/7/16.
 */
public class AppAction extends ActionSupport{
    @Autowired
    public AppService appService;

    @Override
    public String execute() throws Exception {
        List<App> m=appService.getAllApps();
        System.out.println(m);
        System.out.println("aaa");
        return "aaa";

    }

    public String userStatistics() throws Exception {
        List<App> m=appService.getAllApps();
        System.out.println(m);
        System.out.println("ccc");
        return "aaa";

    }
}
