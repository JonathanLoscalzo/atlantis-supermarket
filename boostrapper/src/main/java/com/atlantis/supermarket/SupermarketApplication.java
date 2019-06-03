package com.atlantis.supermarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

//@EnableCaching
@SpringBootApplication
@EnableAsync
@EnableJpaAuditing
@EnableScheduling
@EnableSpringDataWebSupport
public class SupermarketApplication {
   
    public static void main(String[] args) {
	SpringApplication.run(SupermarketApplication.class, args);
    }

}
