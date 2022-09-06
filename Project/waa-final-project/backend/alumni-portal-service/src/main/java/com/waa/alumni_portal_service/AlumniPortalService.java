package com.waa.alumni_portal_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class AlumniPortalService {

    public static void main(String[] args) {
        SpringApplication.run(AlumniPortalService.class, args);
    }

}
