package com.springbootsse.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class AliveController {

    @GetMapping("/alive/**")
    public String getAlive() {
        return new String("Alive");
    }
    

}
