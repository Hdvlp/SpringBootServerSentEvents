package com.springbootsse.demo.controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.springbootsse.demo.service.EmitterManager;
import com.springbootsse.demo.service.OrdersService;

import org.springframework.http.MediaType;


@RestController
@RequestMapping("/api/v1/sse")

public class SseController {

    private EmitterManager emitterManager;
    List<String> orders;

    public SseController (EmitterManager emitterManager,
        OrdersService ordersService){
        this.emitterManager = emitterManager;
        orders = ordersService.getOrders();
    }

    @CrossOrigin("*")
    @GetMapping(value = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe() {

        SseEmitter emitter = this.emitterManager.subscribe();

        new Thread(() -> {
            try {
                
                for (int i = 0; i < orders.size(); i++) { 

                    String messageToSend = orders.get(i);

                    emitter.send(SseEmitter.event().name("order").data(messageToSend));

                    System.out.println(messageToSend);
                    
                    TimeUnit.SECONDS.sleep(2);
 
                     if (i > 18){
                         emitter.complete();
                     }

                }
                
            } catch (IOException | InterruptedException e) {

            } 

        }).start();


        return emitter;
    }

   
}


