package com.example.coursesapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@ComponentScan({
//        "com.example.coursesapp.dao",
//        "com.example.coursesapp.restResource",
//        "com.example.coursesapp.serviceImpl"
//})
public class CoursesAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoursesAppApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext ctx){
//
//
//        return args -> {
//            System.out.println("Let's inspect the beans provided by Spring Boot:");
//
//            String[] beanNames = ctx.getBeanDefinitionNames();
//            for (String beanName : beanNames) {
//                System.out.println(beanName);
//            }
//
//        };
//    }
}
