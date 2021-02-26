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

    dreamRepository.deleteAll();
    themeRepository.deleteAll();
    userRepository.deleteAll();
    toneRepository.deleteAll();

    return args -> {
      User user1 = userRepository.save(new User("Mike Jones", "mjones@example.com"));
      User user2 = userRepository.save(new User("Ava Drew", "adrew@example.com"));

      Dream dream1 = dreamRepository
          .save(new Dream("02/22/2021", "Cool Dream", "This is a good dream", "Happy", user1));
      Dream dream2 = dreamRepository.save(new Dream("02/23/2021", "Bad Dream", "This was scary", "Bad", user1));
      Dream dream3 = dreamRepository.save(new Dream("02/22/2021", "Weird Dream", "This was very weird", "Fun", user2));

      Theme theme1 = themeRepository.save(new Theme(user1, "Bus"));
      Theme theme2 = themeRepository.save(new Theme(user2, "Train"));

      Tone tone1 = toneRepository.save(new Tone("happy", 3, dream1));
      Tone tone2 = toneRepository.save(new Tone("sad", 1, dream2));
      Tone tone3 = toneRepository.save(new Tone("fine", 2, dream3));

      dream1.addTheme(theme1);
      dream2.addTheme(theme1);
      dream3.addTheme(theme2);
    };
  }
}
