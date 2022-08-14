package com.netsec.sm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin
public class SmDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmDemoApplication.class, args);
    }

}
