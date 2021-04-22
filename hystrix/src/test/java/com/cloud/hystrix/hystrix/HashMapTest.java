package com.cloud.hystrix.hystrix;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: xk
 * @Date: 2021/4/7 14:24
 */
public class HashMapTest {

    public static void main(String[] args) throws InterruptedException {
        final Map<String, String> map = new HashMap<String, String>();


        CountDownLatch countDownLatch = new CountDownLatch(10000);

        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    final int ii = i;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
//                            System.out.println("put thread="+ii);
                            map.put(ii + "", "");
                            countDownLatch.countDown();
                        }

                    }, "ftf" + i).start();
                }
            }
        }, "ftf");


        t.start();
        countDownLatch.await();


        System.out.println("map size===========" + map.size());
    }


}
