package com.example.chocolate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class ChocolateApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChocolateApplication.class, args);
    }

    @EnableScheduling
    public class InventoryApplication {
        public static void main(String[] args) {
            SpringApplication.run(InventoryApplication.class, args);
        }
    }
}
