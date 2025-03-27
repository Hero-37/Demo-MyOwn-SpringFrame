package com.minis.test;

/**
 * @author YuLong
 */
public class BaseBaseService {
    private AserviceImpl aserviceImpl;

    public AserviceImpl getAserviceImpl() {
        return aserviceImpl;
    }

    public void setAserviceImpl(AserviceImpl aserviceImpl) {
        this.aserviceImpl = aserviceImpl;
    }

    public void sayHello() {
        System.out.println("BaseBaseService says Hello");
        aserviceImpl.sayHello();
    }
}
