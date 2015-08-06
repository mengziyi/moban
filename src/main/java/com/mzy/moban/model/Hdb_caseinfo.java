package com.mzy.moban.model;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * Created by mengzy on 2015/8/6.
 */
public class Hdb_caseinfo extends BaseModel {


  private String phone;
  private String licensePlate;
  private String personName;

  public String getLicensePlate() {
    return licensePlate;
  }

  public void setLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getPersonName() {
    return personName;
  }

  public void setPersonName(String personName) {
    this.personName = personName;
  }

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this);
  }

}
