package com.beforedeadline.anbuback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AnbuBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnbuBackApplication.class, args);
    }

}
