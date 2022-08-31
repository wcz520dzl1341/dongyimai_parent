package com.offcn.sellergoods.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.offcn.sellergoods.Helloservice;

import javax.annotation.Resource;

/**
 * @author wcz
 * @create 2021-09-24 20:02
 */
@Service
public class HelloServiceImpl implements Helloservice {


    @Override
    public String getName() {
        return "hello 你好";
    }
}
