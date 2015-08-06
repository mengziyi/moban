package com.mzy.moban.model;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

/**
 * Created by mengzy on 2015/7/28.
 */
public class Brandinfo extends BaseModel{
    private String brandNameEn;
    private String brandNameCn;
    private int mileage;
    private int mileage_max;


    @Override
    public boolean equals(Object o) {
        if(this == o)
            return true;
        if(!(o instanceof App))
            return false;
        App another = (App)o;
        return another.getId() == this.getId();
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }


    public String getBrandNameCn() {
        return brandNameCn;
    }

    public void setBrandNameCn(String brandNameCn) {
        this.brandNameCn = brandNameCn;
    }

    public String getBrandNameEn() {
        return brandNameEn;
    }

    public void setBrandNameEn(String brandNameEn) {
        this.brandNameEn = brandNameEn;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getMileage_max() {
        return mileage_max;
    }

    public void setMileage_max(int mileage_max) {
        this.mileage_max = mileage_max;
    }
}
