package com.jack.elasticsearch.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class Test {

    @RequestMapping("/name/{name}")
    public String test(@PathVariable String name){
        return "你好"+name+",么么哒";
    }

}
