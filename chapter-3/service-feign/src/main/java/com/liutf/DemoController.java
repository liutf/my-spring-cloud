package com.liutf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * FileName: DemoController
 * Description:
 * Version: v1.0.0
 * Author: liutf
 * Date: 2018/1/9
 */
@RestController
public class DemoController {

    @Autowired
    private SchedualServiceHi service;

    @GetMapping("/hi/{name}")
    public String hi(@PathVariable String name){
        return service.say(name);
    }

}
