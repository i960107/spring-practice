package com.example.ioc;

import org.springframework.beans.factory.annotation.Qualifier;

public class Encoder {
    private IEncoder iEncoder;

    public Encoder(@Qualifier("base74Encoder") IEncoder iEncoder) {
        this.iEncoder = iEncoder;
    }

    public String encode(String message) {
        return iEncoder.encode(message);

    }

    public void setiEncoder(IEncoder encoder) {
        this.iEncoder = encoder;
    }
}
