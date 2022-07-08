package com.zapu.property.seeder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class PropertySeederApplication {

    public static void main(String[] args) {
        SpringApplication.run(PropertySeederApplication.class, args);
    }

}
