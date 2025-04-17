package com.springbootsse.demo.service;

import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
public class EmitterManager {
    private final ConcurrentHashMap<Integer,SseEmitter> emittersMap = new ConcurrentHashMap<>();
    private Integer keyOfEmitter = 0;

    public SseEmitter subscribe() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        keyOfEmitter++;
        try{
            emittersMap.put(keyOfEmitter, emitter);

            emitter.onCompletion(() -> emittersMap.remove(keyOfEmitter));
            emitter.onTimeout(() -> emittersMap.remove(keyOfEmitter));
    
        } catch (Exception e){
            emittersMap.remove(keyOfEmitter);
        }
        
        return emitter;
    }

    public void sendMessage(String message) {
        Integer key = null;
        try {
            Iterator<Integer> iterator = emittersMap.keySet().iterator();

            while(iterator.hasNext()){
                key = iterator.next();
                emittersMap.get(key).send(SseEmitter.event().data(message));
            }
            return;

        } catch (Exception e) {
            emittersMap.remove(key);
        } 
    }

}
