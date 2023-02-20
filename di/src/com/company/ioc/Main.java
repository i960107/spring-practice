package com.company.ioc;

public class Main {

    public static void main(String[] args) {
        String url = "www.naver.com/book/it?page=10&name=spring-boot";

        // Base 64 Encoding
        IEncoder base64Encoder = new Base64Encoder();
        String res = base64Encoder.encode(url);
        System.out.println(res);

        // url encoding이 필요해졌다면?
        IEncoder urlEncoder = new UrlEncoder();
        String urlRes = urlEncoder.encode(url);
        System.out.println(urlRes);

        //전략 패턴: 본질 Encoder 클래스를 수정하지 않고 인코딩 방식 변경 가능.
        //외부에서 사용할 객체를 주입하고 있으므로 Encoder입장에서는 외부에서 DI 받은 것. 여전히 개발자가 주입하고 있음.
//        Encoder encoder = new Encoder(new Base64Encoder());
        Encoder encoder = new Encoder(new UrlEncoder());
        String result = encoder.encode(url);
        System.out.println(result);


    }
}
