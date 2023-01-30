package com.educationalmanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class EducationalManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(EducationalManageApplication.class, args);
    }

}
