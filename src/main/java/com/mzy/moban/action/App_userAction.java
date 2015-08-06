package com.mzy.moban.action;

import com.mzy.moban.exception.App_userException;
import com.mzy.moban.service.App_userService;
import com.mzy.moban.util.FormatUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * Created by mengzy on 2015/7/16.
 */
public class App_userAction extends InterfAction{
    private static final Logger log = Logger.getLogger(App_userAction.class);
    @Autowired
    public App_userService app_userService;
    public static final String RESULT = "result";
    public String phone;
    public String password;
    public String licensePlate;



    public String register() throws IOException {

        String ret= null;
        System.out.println(phone);
        System.out.println(password);
        System.out.println(licensePlate);
        try {
            ret = app_userService.register(phone,password,licensePlate);
        } catch (Exception e) {
            setResult(false, e.getMessage());

            return "register";
        }

        setResult(true, ret);
        return "register";
    }





  public String aquireAccount() throws IOException {
    String ret= null;
    System.out.println(phone);
    System.out.println(password);
    try {
      ret = app_userService.aquireAccount(phone, password);
    } catch (App_userException e) {
      System.out.println("cccc");
      setResult(false, e.getMessage());
      return "aquireAccount";
    }

    System.out.println("ret:"+ret);
    setResult(true, ret);
    return "aquireAccount";
  }






















    public static void main(String[] args) throws ParseException {
        String a="1990-02-28";
        Date cc= FormatUtils.parseDate(a);
        System.out.println(cc);

        System.out.println((char)63);
        System.out.println((char)61);
        System.out.println((char) 38);



    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

  public String getLicensePlate() {
    return licensePlate;
  }

  public void setLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
  }
}
