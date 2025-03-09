package com.own.service;

import com.spring.*;

/**
 * @author YuLong
 */
@Component("userService")
@Scope("singleton")
public class UserServiceImpl implements BeanNameAware, InitializingBean, UserService {

    @Autowired
    private OrderService orderService;

    private String beanName;

    @Override
    public void setBeanName(String name) {
        beanName = name;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("初始化操作！！！");
    }

    public void test() {
        System.out.println(orderService);
        System.out.println(beanName);
    }

    @Override
    public void test1() {
        System.out.println("实际业务！！！");
    }

    public String getBeanName() {
        return beanName;
    }
}
