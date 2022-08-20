package com.example.reactrouterdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySource("file:/Users/sandeep/.dot_files/java/application.properties")
public class ReactRouterDemoApplication {

    public static void main(String[] args) {
        System.out.println("Hello World");
        SpringApplication.run(ReactRouterDemoApplication.class, args);
    }

}
