package com.demo.employees;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.demo.employees.service", "com.demo.employees.controller"})
@EntityScan(basePackages = {"com.demo.employees.entity"})
@EnableJpaRepositories(basePackages = {"com.demo.employees.repository"})
public class EmployeesApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeesApplication.class, args);
    }

}
