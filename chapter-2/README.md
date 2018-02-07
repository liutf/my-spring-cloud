
## Ribbon简介

> Ribbon是Netflix发布的开源项目，主要功能是提供客户端的软件负载均衡算法，将Netflix的中间层服务连接在一起。Ribbon客户端组件提供一系列完善的配置项如连接超时，重试等。
>
> 简单的说，就是在配置文件中列出Load Balancer（简称LB）后面所有的机器，Ribbon会自动的帮助你基于某种规则（如简单轮询，随机连接等）去连接这些机器。我们也很容易使用Ribbon实现自定义的负载均衡算法。

​									<!-- more -->

## 准备工作

* 下文中需引用到 [上篇](https://www.liutf.com/20180116/spring-cloud-chapter-1.html) 中`eureka-server` 、`eureka-client`  模块。

* 再复制一个`eureka-client` 模块，命名为`service-hi` ，将`application.yml` 配置改下端口即可：

  ```yaml

  eureka:
    client:
      service-url:
        defaultZone: http://localhost:8761/eureka/

  server:
    port: 8763

  spring:
    application:
      name: service-hi
  ```

  ​



## 创建服务消费者

* 创建一个Module，命名为`eureka-ribbon` 。

* 启动类`ServiceRibbonApplication.java` 中添加注解`@EnableDiscoveryClient` 。

* 初始化Bean`RestTemplate` ，加上`@LoadBalanced`注解 ，开启应用可自动负载均衡。

  ```java
  import org.springframework.boot.SpringApplication;
  import org.springframework.boot.autoconfigure.SpringBootApplication;
  import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
  import org.springframework.cloud.client.loadbalancer.LoadBalanced;
  import org.springframework.context.annotation.Bean;
  import org.springframework.web.bind.annotation.RestController;
  import org.springframework.web.client.RestTemplate;

  @SpringBootApplication
  @EnableDiscoveryClient
  public class ServiceRibbonApplication {

  	public static void main(String[] args) {
  		SpringApplication.run(ServiceRibbonApplication.class, args);
  	}

      @Bean
      @LoadBalanced
      RestTemplate restTemplate() {
          return new RestTemplate();
      }

  }
  ```

  ​

* 创建Controller，接收用户请求。

  ```java

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
      private HelloService service;

      @GetMapping("/hi/{name}")
      public String hi(@PathVariable String name){
          return service.say(name);
      }

  }
  ```

  ​

* 创建Service类，调用我们之前在`Eureka`  注册的服务 `SERVICE-HI` 。

  ```java
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
  ```

* 启动`eureka-server` 和 `eureka-client` 。

* 访问Ribbon客户端入口： [http://localhost:8764/hi/aa](http://localhost:8764/hi/aa) ，调用到了8762的服务。

  ![](http://p2oheco53.bkt.clouddn.com/201801191727_590.png) 

  ### Ribbon负载均衡

  * 启动 `service-hi` ，`Eureka` 注册中心名为`SERICVE-HI` 的服务注册了两个实例了。

    ![](http://p2oheco53.bkt.clouddn.com/201801231625_762.png)

  * 再访问Ribbon客户端入口： [http://localhost:8764/hi/aa](http://localhost:8764/hi/aa) ，验证负载均衡，成功。

    ![](http://p2oheco53.bkt.clouddn.com/201801191727_590.png)

    ![](http://p2oheco53.bkt.clouddn.com/201801231627_649.png)

    ​

  ​