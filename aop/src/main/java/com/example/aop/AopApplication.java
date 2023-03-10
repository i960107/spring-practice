package com.example.aop;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AopApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopApplication.class, args);
        System.out.println(Base64.getEncoder().encodeToString("i960107@naver.com".getBytes(StandardCharsets.UTF_8)));
    }

}
