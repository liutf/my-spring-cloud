## Spring Cloud 入门教程：服务注册与发现 - Eureka



### 创建服务注册中心

* 创建一个Module，命名为`eureka-server` 。

  ![](http://7viho5.com1.z0.glb.clouddn.com/201801161548_687.png)

  ​

  ![](http://7viho5.com1.z0.glb.clouddn.com/201801161550_115.png)

  ​

  ![](http://7viho5.com1.z0.glb.clouddn.com/201801161551_741.png)

* 点击Finish，即可完成module创建。

* 根目录`settings.gradle` 自动添加了模块之间的依赖关系。

  ![](http://7viho5.com1.z0.glb.clouddn.com/201801161556_623.png)

* 在module内的`build.gradle` 中添加依赖：

```  groovy
group 'com.liutf'
version '0.0.1-SNAPSHOT'

apply plugin: 'java'

sourceCompatibility = 1.8

repositories {
    mavenCentral()
}

dependencies {
    compile('org.springframework.cloud:spring-cloud-starter-eureka-server')
    testCompile group: 'junit', name: 'junit', version: '4.12'
}

```



* `EurekaServerApplication.java` 启动类中添加注解：`@EnableEurekaServer` 

  ```java
  import org.springframework.boot.SpringApplication;
  import org.springframework.boot.autoconfigure.SpringBootApplication;
  import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

  @SpringBootApplication
  @EnableEurekaServer
  public class EurekaServerApplication {

  	public static void main(String[] args) {
  		SpringApplication.run(EurekaServerApplication.class, args);
  	}
  }
  ```

  ​

* `application.yml` 添加配置

  ```yaml
  server:
    port: 8761

  eureka:
    instance:
      hostname: localhost
    client:
      register-with-eureka: false
      fetch-registry: false
      service-url:
        defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
        
  ```

* 启动服务，访问 [http://localhost:8761/](http://localhost:8761/)

  ![](http://7viho5.com1.z0.glb.clouddn.com/201801161605_752.png)



### 创建服务提供方

* 创建一个Module，命名为`eureka-client` 。创建过程同上。

* `EurekaClientApplication.java` 启动类添加注解：`@EnableEurekaClient` `@RestController` 

* 添加一个`hi`方法，用于后续验证提供的服务。

  ```java
  import org.springframework.beans.factory.annotation.Value;
  import org.springframework.boot.SpringApplication;
  import org.springframework.boot.autoconfigure.SpringBootApplication;
  import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
  import org.springframework.web.bind.annotation.GetMapping;
  import org.springframework.web.bind.annotation.PathVariable;
  import org.springframework.web.bind.annotation.RestController;

  @SpringBootApplication
  @EnableEurekaClient
  @RestController
  public class EurekaClientApplication {

  	public static void main(String[] args) {
  		SpringApplication.run(EurekaClientApplication.class, args);
  	}

  	@Value("${server.port}")
  	String port;

  	@GetMapping("/hi/{name}")
  	public String hi(@PathVariable String name) {
  		return "hi "+name+",i am from port:" +port;
  	}

  }
  ```

  ​

* `application.yml` 配置文件添加配置

  配置本应用端口为8762，名为`service-hi`的应用以微服务形式注册到Eureka的服务注册地址 [http://localhost:8761/eureka/](http://localhost:8761/eureka/)

  ```yaml

  eureka:
    client:
      service-url:
        defaultZone: http://localhost:8761/eureka/

  server:
    port: 8762

  spring:
    application:
      name: service-hi
  ```

* 启动服务。访问[http://localhost:8762/hi/jack](http://localhost:8762/hi/jack) ，Controller正常返回。

  ![](http://7viho5.com1.z0.glb.clouddn.com/201801161630_405.png) 

* 再访问Eureka： [http://localhost:8761/](http://localhost:8761/) ，此时，我们的服务已注册到Eureka，注册完成。

  ![](http://7viho5.com1.z0.glb.clouddn.com/201801161620_592.png) 



