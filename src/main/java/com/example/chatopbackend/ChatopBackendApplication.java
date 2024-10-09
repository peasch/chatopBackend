package com.example.chatopbackend;

import com.example.chatopbackend.repository.UserDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.PrintStream;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.*"})
@EnableJpaRepositories(basePackages = {"com.example.*"})
public class ChatopBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatopBackendApplication.class, args);
    }

}
