# Spring Cloud入门教程



## 教程目录

入门篇：

* Spring Cloud 入门教程：服务注册与发现 - Eureka
* Spring Cloud 入门教程：服务消费者 - REST + Ribbon
* Spring Cloud 入门教程：服务消费者 - Feign
* Spring Cloud 入门教程：断路器 - Hystrix
* Spring Cloud 入门教程：路由网关 - Zuul
* Spring Cloud 入门教程：分布式配置中心 - Spring Cloud Config
* Spring Cloud 入门教程：消息总线 - Spring Cloud Bus
* Spring Cloud 入门教程：服务链路追踪 - Spring Cloud Sleuth
* ……



进阶篇：

* 高可用的服务注册中心
* ……




## 说明

教程内示例代码使用IDEA + Gradle构建。

源码地址：https://github.com/liutf/my-spring-cloud

组件相关版本：

- Spring Cloud版本：Dalston.RELEASE
- Spring Boot：1.5.8.RELEASE

* 工程代码结构

  ├── build.gradle
  ├── chapter-1
  │   ├── eureka-client
  │   ├── eureka-server
  ├── chapter-2
  │   ├── service-hi
  │   ├── service-ribbon
  ├── chapter-3
  │   ├── service-feign
  ├── chapter-4
  │   ├── hystrix-demo
  ├── chapter-5
  │   ├── service-zuul
  ├── chapter-6
  │   ├── config-client
  │   ├── config-server
  ├── chapter-7
  │   ├── server-zipkin
  │   ├── service-hi
  │   ├── service-miya
  ├── chapter-8
  │   ├── eureka-client
  │   ├── eureka-server-multiple
  ├── dependencyDefinitions.gradle
  ├── gradle
  │   └── wrapper
  ├── gradlew
  ├── gradlew.bat
  ├── LICENSE
  ├── README.md
  └── settings.gradle

  ​

* 根build.gradle配置

  ```groovy
  buildscript {
      ext {
          springBootVersion = '1.5.8.RELEASE'
      }
      repositories {
          mavenCentral()
      }
      dependencies {
          classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
      }
  }

  ext {
      springCloudVersion = 'Dalston.RELEASE'
  }

  allprojects {
      repositories {
          mavenCentral()
      }
      group = 'com.liutf'
      version = '0.0.1-SNAPSHOT'
  }


  subprojects {
      apply plugin: 'java'
      apply plugin: 'idea'
      apply plugin: 'eclipse'
      apply plugin: 'org.springframework.boot'

      sourceCompatibility = 1.8
      [compileJava, compileTestJava]*.options*.encoding = 'UTF-8'

      dependencyManagement {
          imports {
              mavenBom "org.springframework.cloud:spring-cloud-dependencies:${springCloudVersion}"
          }
      }

      dependencies {
          compileOnly('org.projectlombok:lombok')
          compile 'com.google.guava:guava:23.6-jre'
          compile('org.springframework.boot:spring-boot-starter-web')
          compile('org.springframework.cloud:spring-cloud-starter-eureka')
          testCompile('org.springframework.boot:spring-boot-starter-test')
      }
  }
  ```

  ​