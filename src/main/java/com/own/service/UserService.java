package com.own.service;

import com.spring.Autowired;
import com.spring.Component;
import com.spring.Scope;

/**
 * @author YuLong
 */
@Component("userService")
@Scope("singleton")
public class UserService {

    @Autowired
    private OrderService orderService;

    public void test() {
        System.out.println(orderService);
    }
}
