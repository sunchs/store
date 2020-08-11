package com.sunchs.store.receive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.sunchs.store.db","com.sunchs.store.receive"})
public class ReceiveApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReceiveApplication.class, args);
    }
}