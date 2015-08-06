package com.mzy.moban.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mzy.moban.exception.App_userException;
import com.mzy.moban.mapper.App_userMapper;
import com.mzy.moban.mapper.Hdb_caseinfoMapper;
import com.mzy.moban.model.App_user;
import com.mzy.moban.model.Hdb_caseinfo;
import com.mzy.moban.util.MD5;
import org.apache.commons.collections.map.HashedMap;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mengzy on 2015/7/16.
 */

@Service
public class App_userService {
    @Autowired
    public App_userMapper<App_user> app_userMapper;
  @Autowired
  public Hdb_caseinfoMapper<Hdb_caseinfo> hdb_caseinfoMapper;
  @Autowired
  public Hdb_caseinfoService hdb_caseinfoService;

  public List<App_user> app_userList;
  public App_user app_user;
  public Hdb_caseinfo hdb_caseinfo;
    private static final Logger log = Logger.getLogger(App_userService.class);

    public  String register(String phone, String pwd,String license) throws App_userException, IOException {
      Hdb_caseinfo hdb_caseinfo=hdb_caseinfoService.getHdbByPhoneAndLicense(phone,license);
      if(hdb_caseinfo==null){
        throw new App_userException("在店内未查到该用户数据");
      }

        HttpHost proxy = new HttpHost("localhost", 8888, "http");
        org.apache.http.client.HttpClient httpClient = new DefaultHttpClient();
        httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
        String encoding = "UTF-8";
        JSONObject t = new JSONObject();
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        HttpPost post = new HttpPost("http://a1.easemob.com/mengzy/ucar/users");
      app_user=new App_user();
      app_user.setApp_account(phone);
      app_userList=app_userMapper.getByAppAccount(phone);
     String responseString="";
      if(app_userList.size()>0){
        throw new App_userException("用户名已经存在");
      }
      else{
        int customerId=hdb_caseinfo.getId();
        String secret="uc";
        String Easemob_accout=MD5.encode(phone + secret + license);
        String Easemob_secret=MD5.encode(pwd+secret);

        t.put("username",Easemob_accout );
        t.put("password",Easemob_secret );
        String q = JSON.toJSONString(t);
        StringEntity entity = new StringEntity(q);
        String param = URLEncodedUtils.format(nvps, encoding);
        post.setEntity(entity);
        log.info("executing request " + post.getURI() + post.getEntity().getContentType());

        HttpResponse response = httpClient.execute(post);
        int status = response.getStatusLine().getStatusCode();
         responseString = EntityUtils.toString(response.getEntity());
        System.out.println(responseString);
        JSONObject w = JSONObject.parseObject(responseString);
        if (status != 200) {
          log.error("fail---->" + w.get("error_description"));
          return w.get("error_description").toString();
        } else {
          log.info("success----->" + responseString);
          app_user.setApp_account(phone);
          app_user.setApp_secret(pwd);
          app_user.setEasemob_accout(Easemob_accout);
          app_user.setEasemob_secret(Easemob_secret);
          app_user.setCustomer_id(customerId + "");
          app_userMapper.insert(app_user);
          Map retmap=new HashMap<>();
          retmap.put("app_user",app_user);
          retmap.put("hdb_caseinfo",hdb_caseinfo);
          responseString= JSON.toJSONString(retmap);
        }


        httpClient.getConnectionManager().shutdown();
      }

        return responseString;
    }


  public String aquireAccount(String phone,String password) throws App_userException {
Map param=new HashedMap();
    param.put("phone",phone);
    param.put("password",password);
    System.out.println(param);

    app_userList= app_userMapper.getByAppAccountAndPwd(param);
    String responseString="";
    if(app_userList.size()>0){
      app_user= app_userList.get(0);
      System.out.println(app_user);
      hdb_caseinfo=new Hdb_caseinfo();
      hdb_caseinfo.setId(Integer.parseInt(app_user.getCustomer_id()));
      System.out.println(hdb_caseinfo);
      hdb_caseinfo= hdb_caseinfoMapper.get(hdb_caseinfo).get(0);
      Map retmap=new HashMap<>();
      retmap.put("app_user",app_user);
      retmap.put("hdb_caseinfo",hdb_caseinfo);
      responseString= JSON.toJSONString(retmap);
      System.out.println(responseString);
    }
    else{
      throw new App_userException("用户名或密码错误");
    }

    return responseString;
  }






























    public static void main(String[] args) {

    }
}



























