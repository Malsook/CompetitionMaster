package com.hzu.competition_master;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Import;
@SpringBootApplication
public class CompetitionMasterApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext run = SpringApplication.run(CompetitionMasterApplication.class, args);


    }

}
