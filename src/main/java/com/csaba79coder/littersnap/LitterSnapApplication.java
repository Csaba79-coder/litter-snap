package com.csaba79coder.littersnap;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LitterSnapApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(LitterSnapApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
