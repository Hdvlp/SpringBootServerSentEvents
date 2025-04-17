package com.springbootsse.demo.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class OrdersService {

    private static int orderNum = 0;

    public List<String> getOrders(){
        List<String> orders = new ArrayList<>();
        Instant instime = Instant.now();

        String[] orderStatus = new String[]{
            "pending",
            "complete"
        };

        for (int i=0; i < 20; i++){
            orderNum++;
            String tmpOrderStatus = orderStatus[ (int)(Math.random() * orderStatus.length)];
            orders.add(String.format(
                "{ \"id\": \"%d\", \"time\" : \"%s\", \"OrderStatus\": \"%s\" }",
                orderNum,
                String.valueOf(instime),
                tmpOrderStatus));
        }

        return orders;
    }

}
