package com.minis.test;

import com.minis.beans.factory.annotation.Autowired;

/**
 * @author YuLong
 */
public class BaseService {
    @Autowired
    private BaseBaseService basebaseservice;

    public BaseService() {
    }

    public BaseBaseService getBaseBaseService() {
        return basebaseservice;
    }

    public void setBaseBaseService(BaseBaseService baseBaseService) {
        this.basebaseservice = baseBaseService;
    }

    public void sayHello() {
        System.out.println("Base Service says Hello");
        basebaseservice.sayHello();
    }
}
