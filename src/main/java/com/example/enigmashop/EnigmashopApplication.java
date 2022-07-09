package com.example.enigmashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//@EnableWebMvc
@SpringBootApplication
public class EnigmashopApplication {

    public static void main(String[] args) {
        SpringApplication.run(EnigmashopApplication.class, args);
    }

}
