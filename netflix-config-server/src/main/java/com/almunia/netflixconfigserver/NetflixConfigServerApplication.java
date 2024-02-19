package com.almunia.netflixconfigserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class NetflixConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NetflixConfigServerApplication.class, args);
    }

}
