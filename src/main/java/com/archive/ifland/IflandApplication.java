package com.archive.ifland;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class IflandApplication {

  public static void main(String[] args) {
    SpringApplication.run(IflandApplication.class, args);
  }

}
