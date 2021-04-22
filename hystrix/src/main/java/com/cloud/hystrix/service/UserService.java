package com.cloud.hystrix.service;

import com.cloud.hystrix.vo.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: xk
 * @Date: 2021/1/22 15:03
 */
@Service
@Log4j2
public class UserService {


    /**
     * https://github.com/Netflix/Hystrix/wiki/Configuration
     *
     * execution.isolation.thread.timeoutInMilliseconds 超时等待时间，默认1秒
     * circuitBreaker.requestVolumeThreshold 时间窗口内的请求量，默认20
     * circuitBreaker.sleepWindowInMilliseconds 熔断后的半开放时间，默认5秒
     * maximumSize 线程池数量最大值，默认10
     * metrics.rollingStats.timeInMilliseconds 滚动时间窗口秒数，默认10秒
     * metrics.rollingStats.numBuckets 滚动桶数，默认10，所以是一秒一个桶
     * 断路器根据时间窗口的请求量，错误数来决定是否打开，默认是50%
     *
     * groupKey默认是类名，commandKey默认是方法名，每个grouopKey对应一个线程池，如需要给特定的commandKey指定线程池则需要
     * 指定ThreadPoolKey
     *
     * @return
     */
    @HystrixCommand(fallbackMethod = "listUsersFallback")
    public List<User> listUsers() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("interruptedException");
        }

        return null;
    }



    @HystrixCommand(fallbackMethod = "listUsersFallback")
    public List<User> listUsers3() {
        throw new RuntimeException();
    }



    @HystrixCommand(fallbackMethod = "listUsersFallback", ignoreExceptions = {Exception.class})
    public List<User> listUsers4() throws Exception {
        throw new RuntimeException();
    }



    @HystrixCommand(fallbackMethod = "listUsersFallback", groupKey = "userService2")
    public List<User> listUsers2() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            log.error("interruptedException2");
        }

        return null;
    }

    public List<User> listUsersFallback(){
        return Arrays.asList(User.builder().name("fallback").password("aaa").build());
    }

}
