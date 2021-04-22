package com.cloud.hystrix;

import com.cloud.hystrix.service.UserService;
import com.cloud.hystrix.vo.User;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.HystrixCircuitBreakerFactory;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
@EnableCircuitBreaker
@RestController
@Log4j2
@EnableHystrixDashboard
public class HystrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixApplication.class, args);
    }


    @Autowired
    private UserService userService;

    @RequestMapping("/getUser")
    @ResponseBody
    public List<User> listUser(){

        log.info("getUser........");
        List<User> result = Collections.emptyList();
        try {
            result = userService.listUsers();
            return result;
        } finally {
            log.info("response:"+result);
        }

    }


    @RequestMapping("/getUser2")
    @ResponseBody
    public List<User> listUser2(){

        log.info("getUser2........");
        List<User> result = Collections.emptyList();
        try {
            result = userService.listUsers2();
            return result;
        } finally {
            log.info("2response:"+result);
        }

    }



    @RequestMapping("/getUser3")
    @ResponseBody
    public List<User> listUser3(){

        log.info("getUser3........");
        List<User> result = Collections.emptyList();
        try {
            result = userService.listUsers3();
            return result;
        } finally {
            log.info("3response:"+result);
        }

    }

    @RequestMapping("/getUser4")
    @ResponseBody
    public List<User> listUser4(){

        log.info("getUser4........");
        List<User> result = Collections.emptyList();
        try {
            result = userService.listUsers4();
        }catch (Exception e) {
            log.info("error:"+result);
        } finally {

            log.info("4response:"+result);
        }

        return result;
    }
}
