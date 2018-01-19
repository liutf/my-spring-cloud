package com.liutf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * FileName: HelloServiceImpl
 * Description:
 * Version: v1.0.0
 * Author: liutf
 * Date: 2018/1/9
 */
@Service
public class HelloServiceImpl implements HelloService {

    @Autowired
    RestTemplate restTemplate;

    @Override
    public String say(String name){
        String url = "http://SERVICE-HI/hi";
        String result = restTemplate.getForObject(url + "/" + name, String.class);
        return result;
    }

}
