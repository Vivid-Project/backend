package com.vivid.backend;

import com.vivid.backend.model.Dream;
import com.vivid.backend.model.Theme;
import com.vivid.backend.model.Tone;
import com.vivid.backend.model.User;
import com.vivid.backend.repository.DreamRepository;
import com.vivid.backend.repository.ThemeRepository;
import com.vivid.backend.repository.ToneRepository;
import com.vivid.backend.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SeedDatabase {

  @Bean
  CommandLineRunner initDatabase(UserRepository userRepository, DreamRepository dreamRepository,
      ThemeRepository themeRepository, ToneRepository toneRepository

  ) {

    if (userRepository.findAll().isEmpty()) {
      dreamRepository.deleteAll();
      themeRepository.deleteAll();
      userRepository.deleteAll();

      return args -> {
        User user1 = userRepository.save(new User("Mike Jones", "mjones@example.com", "password"));
        User user2 = userRepository.save(new User("Ava Drew", "adrew@example.com", "password"));

        Dream dream1 = dreamRepository.save(new Dream("2021/02/23", "Cool Dream", "This is a good dream", "Happy", user1));
        Dream dream2 = dreamRepository.save(new Dream("2021/02/24", "Bad Dream", "This was scary", "Bad", user1));
        Dream dream3 = dreamRepository.save(new Dream("2021/02/25", "Ok Dream", "This dream was meh", "Meh", user1));
        Dream dream4 = dreamRepository.save(new Dream("2021/02/23", "Weird Dream", "This was very weird", "Fun", user2));

        Theme theme1 = themeRepository.save(new Theme(user1, "Bus"));
        Theme theme2 = themeRepository.save(new Theme(user2, "Train"));

        Tone tone1 = toneRepository.save(new Tone("Fun", 2L, dream1));

        dream1.addTheme(theme1);
        dream2.addTheme(theme1);
        dream3.addTheme(theme2);
        dream4.addTheme(theme2);
      };

    } else {
      return null;
    }

  }
}
