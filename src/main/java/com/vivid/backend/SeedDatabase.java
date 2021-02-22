package com.vivid.backend;

import com.vivid.backend.model.User;
import com.vivid.backend.repository.UserRepository;
import com.vivid.backend.model.Dream;
import com.vivid.backend.repository.DreamRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SeedDatabase {

  private static final Logger log = LoggerFactory.getLogger(SeedDatabase.class);

  @Bean
  CommandLineRunner initDatabase(UserRepository userRepository, DreamRepository dreamRepository) {
    dreamRepository.deleteAll();
    userRepository.deleteAll();

    return args -> {
      User user1 = userRepository.save(new User("Mike Jones", "mjones@example.com"));
      User user2 = userRepository.save(new User("Ava Drew", "adrew@example.com"));

      Dream dream1 = dreamRepository.save(new Dream("02/22/2021", "Cool Dream", "This is a good dream", "Happy", user1));
      Dream dream2 = dreamRepository.save(new Dream("02/23/2021", "Bad Dream", "This was scary", "Bad", user1));
      Dream dream3 = dreamRepository.save(new Dream("02/22/2021", "Weird Dream", "This was very weird", "Fun", user2));
    };
  }
}