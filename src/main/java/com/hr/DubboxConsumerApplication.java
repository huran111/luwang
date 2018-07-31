package com.hr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:spring/spring-consumer.xml")
public class DubboxConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DubboxConsumerApplication.class, args);
    }
}
