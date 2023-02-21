package com.example.ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class IocApplication {

    public static void main(String[] args) {
        SpringApplication.run(IocApplication.class, args);
        ApplicationContext context = ApplicationContextProvider.getContext();

        String url = "www.naver.com/book/it?page=10&size=20&name=spring-boot";

//        Base64Encoder base64Encoder = context.getBean(Base64Encoder.class);
//        Encoder encoder = new Encoder(base64Encoder);
        //new 로 객체 생성하는 부분 다 제거 -> 모든 권한이 Spring으로 넘어감
        Encoder encoder = context.getBean("base64Encode", Encoder.class);
        String result = encoder.encode(url);
        System.out.println(result);

//        UrlEncoder urlEncoder = context.getBean(UrlEncoder.class);
//        encoder.setiEncoder(urlEncoder);
//        String urlResult = encoder.encode(url);
//        System.out.println(urlResult);
    }

}

@Configuration
class AppConfig {

    //'@Configuration.enforceUniqueMethods' = 'true'.
    //메소드 이름 달라야함
    @Bean("base64Encode")
    public Encoder base64Encoder(Base64Encoder base64Encoder) {
        return new Encoder(base64Encoder);
    }

    @Bean("urlEncode")
    public Encoder urlEencoder(UrlEncoder urlEncoder) {
        return new Encoder(urlEncoder);
    }


}