package com.offcn.sellergoods.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.offcn.sellergoods.Helloservice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author wcz
 * @create 2021-09-24 20:10
 */
@RestController
@RequestMapping("hello")
public class HelloController {
    @Reference
    private Helloservice helloservice;
   @RequestMapping("hello")
   public String getName(){
       return helloservice.getName();
   };

}
