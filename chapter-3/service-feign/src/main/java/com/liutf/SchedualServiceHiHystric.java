package com.liutf;

import org.springframework.stereotype.Component;

/**
 * FileName: SchedualServiceHiHystric
 * Description:
 * Version: v1.0.0
 * Author: liutf
 * Date: 2018/1/10
 */
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {

    @Override
    public String say(String name) {
        return "Sorry, " + name;
    }
}
