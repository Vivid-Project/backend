package com.vivid.backend.controller;

import java.util.List;

import com.vivid.backend.filters.ThemeFilters;
import com.vivid.backend.model.Theme;
import com.vivid.backend.repository.DreamRepository;
import com.vivid.backend.repository.ThemeRepository;
import com.vivid.backend.repository.UserRepository;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThemeController {
  private final UserRepository userRepository;
  private final DreamRepository dreamRepository;
  private final ThemeRepository themeRepository;

  ThemeController(UserRepository userRepository, DreamRepository dreamRepository, ThemeRepository themeRepository) {
    this.userRepository = userRepository;
    this.dreamRepository = dreamRepository;
    this.themeRepository = themeRepository;
  }

  @GetMapping("/themes")
  public MappingJacksonValue getThemes() {
    List<Theme> themes = themeRepository.findAll();
    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(themes);

    mappingJacksonValue.setFilters(ThemeFilters.THEME_DEFAULT_FILTER);
    
    return mappingJacksonValue;
  }
}
