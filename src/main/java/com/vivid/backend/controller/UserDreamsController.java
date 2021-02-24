package com.vivid.backend.controller;

import java.util.Set;

import com.vivid.backend.exceptions.DreamNotFoundException;
import com.vivid.backend.exceptions.UserNotFoundException;
import com.vivid.backend.filters.DreamFilters;
import com.vivid.backend.model.Dream;
import com.vivid.backend.repository.DreamRepository;
import com.vivid.backend.repository.UserRepository;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("users")
@RestController
class UserDreamsController {

  private final UserRepository userRepository;
  private final DreamRepository dreamRepository;

  UserDreamsController(UserRepository userRepository, DreamRepository dreamRepository) {
    this.userRepository = userRepository;
    this.dreamRepository = dreamRepository;
  }

  @GetMapping("{userId}/dreams")
  public MappingJacksonValue getUsersDreamsFiltered(@PathVariable Long userId) {

    Set<Dream> dreams = dreamRepository.findAllByUserId(userId);
    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(dreams);

    mappingJacksonValue.setFilters(DreamFilters.DREAM_DEFAULT_FILTER);

    return mappingJacksonValue;
  }

  @GetMapping("{userId}/dreams/{dreamId}")
  public MappingJacksonValue getUserDreamFiltered(@PathVariable Long userId, @PathVariable Long dreamId) {

    userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

    Dream dream = dreamRepository.findByUserIdAndId(userId, dreamId)
        .orElseThrow(() -> new DreamNotFoundException(dreamId));
    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(dream);

    mappingJacksonValue.setFilters(DreamFilters.DREAM_INCLUDE_USER_FILTER);

    return mappingJacksonValue;
  }
}
