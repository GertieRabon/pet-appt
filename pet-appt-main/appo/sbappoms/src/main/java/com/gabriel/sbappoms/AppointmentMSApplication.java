package com.gabriel.appoms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
        "com.gabriel.sbappoms",   // controllers
        "com.gabriel.appoms",     // services + security
        "com.gabriel.appodata"    // repositories
})
@EntityScan("com.gabriel.appodata.entity")
@EnableJpaRepositories("com.gabriel.appodata.repository")

public class AppointmentMSApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppointmentMSApplication.class, args);
    }

}