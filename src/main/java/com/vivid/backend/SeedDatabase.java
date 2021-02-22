package com.vivid.backend;

import com.vivid.backend.model.User;
import com.vivid.backend.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SeedDatabase {

  private static final Logger log = LoggerFactory.getLogger(SeedDatabase.class);

  @Bean
  CommandLineRunner initDatabase(UserRepository repository) {

    return args -> {
      log.info("Preloading " + repository.save(new User("Mike Jones", "mjones@example.com")));
      log.info("Preloading " + repository.save(new User("Ava Drew", "adrew@example.com")));
    };
  }
}
