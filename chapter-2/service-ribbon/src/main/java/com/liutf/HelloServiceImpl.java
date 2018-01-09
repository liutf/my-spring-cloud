package com.liutf;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

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
        HashMap<String, Object> param = Maps.newHashMap();
        param.put("name","name");
//        return restTemplate.getForObject(url, String.class, param);
        String result = restTemplate.getForObject(url + "?name=" + name, String.class);
        return result;
    }


}
