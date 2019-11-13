package com.unicorn.demoboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by 7 on 2017/08/14.
 */
@SpringBootApplication(scanBasePackages = "com.unicorn")
@MapperScan(basePackages = "com.unicorn.demoboot.dal.mybatis.mapper")
public class Application {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
