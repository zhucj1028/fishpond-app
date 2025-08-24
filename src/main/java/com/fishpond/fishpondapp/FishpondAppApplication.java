package com.fishpond.fishpondapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fishpond.fishpondapp.business.**.mapper")
public class FishpondAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FishpondAppApplication.class, args);
    }

}
