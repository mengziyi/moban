package com.mzy.moban.service;

import com.mzy.moban.mapper.AppMapper;
import com.mzy.moban.model.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mengzy on 2015/7/16.
 */

@Service
public class AppService {
@Autowired
    public AppMapper appMapper;



    public List<App> getAllApps(){
        System.out.println("dd");
return appMapper.get(null);

    }




}
