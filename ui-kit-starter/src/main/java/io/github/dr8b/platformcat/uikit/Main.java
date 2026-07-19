package io.github.dr8b.platformcat.uikit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

@SpringBootConfiguration
@EnableAutoConfiguration
public class Main {

    public static void main(String[] args) {
        System.setProperty("io.github.dr8b.platformcat.ui-kit-starter.gallery", "true");
        SpringApplication.run(Main.class, args);
    }
}
