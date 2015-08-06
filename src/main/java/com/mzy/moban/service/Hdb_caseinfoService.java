package com.mzy.moban.service;

import com.mzy.moban.mapper.Hdb_caseinfoMapper;
import com.mzy.moban.model.Hdb_caseinfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mengzy on 2015/7/16.
 */

@Service
public class Hdb_caseinfoService {
    @Autowired
    public Hdb_caseinfoMapper<Hdb_caseinfo> hdb_caseinfoMapper;
  public List<Hdb_caseinfo> hdb_caseinfoList;

    private static final Logger log = Logger.getLogger(Hdb_caseinfoService.class);



public Hdb_caseinfo getHdbByPhoneAndLicense(String phone,String licensePlate){
Map p=new HashMap<>();
  p.put("phone",phone);
  p.put("licensePlate", licensePlate);
  hdb_caseinfoList= hdb_caseinfoMapper.getByPhoneAndLicense(p);
  if(hdb_caseinfoList.size()>0){
    return hdb_caseinfoList.get(0);
  }
  return null;
}




















    public static void main(String[] args) {

    }
}



























