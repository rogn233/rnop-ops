package com.boco;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.boco.mybatis.dao")
public class RnopOpsApplication {
    public static void main(String[] args) {
        SpringApplication.run(RnopOpsApplication.class, args);
    }
}
