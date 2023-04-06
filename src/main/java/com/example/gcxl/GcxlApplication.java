package com.example.gcxl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.gcxl.mapper")
public class GcxlApplication {

    public static void main(String[] args) {
        SpringApplication.run(GcxlApplication.class, args);
    }

}
