package com.vivid.backend.controller;

import java.util.Map;
import java.util.Set;

import com.vivid.backend.exceptions.DreamNotFoundException;
import com.vivid.backend.exceptions.UserNotFoundException;
import com.vivid.backend.filters.DreamFilters;
import com.vivid.backend.helpers.UserAuthenticationHelper;
import com.vivid.backend.model.Dream;
import com.vivid.backend.model.User;
import com.vivid.backend.repository.DreamRepository;
import com.vivid.backend.repository.UserRepository;

import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
=======
import org.springframework.web.bind.annotation.RequestHeader;
>>>>>>> a0160d477423c9276c9dc41790931551dd2a2e1b
import org.springframework.web.bind.annotation.RestController;

@RestController
class UserDreamsController {

  private final UserRepository userRepository;
  private final DreamRepository dreamRepository;
<<<<<<< HEAD
  private final BasicJsonParser basicJsonParser;
=======
  private final UserAuthenticationHelper userAuthenticationHelper;
>>>>>>> a0160d477423c9276c9dc41790931551dd2a2e1b

  UserDreamsController(UserRepository userRepository, DreamRepository dreamRepository, BasicJsonParser basicJsonParser) {
    this.userRepository = userRepository;
    this.dreamRepository = dreamRepository;
<<<<<<< HEAD
    this.basicJsonParser = basicJsonParser;
=======
    this.userAuthenticationHelper = new UserAuthenticationHelper(this.userRepository);
>>>>>>> a0160d477423c9276c9dc41790931551dd2a2e1b
  }

  @GetMapping("/dreams")
  public MappingJacksonValue getUsersDreamsFiltered(@RequestHeader Map<String, Object> headers) {

    User user = userAuthenticationHelper.authorize(headers.get("authorization").toString());

    Set<Dream> dreams = dreamRepository.findAllByUser(user);
    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(dreams);

    mappingJacksonValue.setFilters(DreamFilters.DREAM_DEFAULT_FILTER);

    return mappingJacksonValue;
  }

  @GetMapping("dreams/{dreamId}")
  public MappingJacksonValue getUserDreamFiltered(@RequestHeader Map<String, Object> headers,
      @PathVariable Long dreamId) {

    User user = userAuthenticationHelper.authorize(headers.get("authorization").toString());

    Dream dream = dreamRepository.findByUserAndId(user, dreamId).orElseThrow(() -> new DreamNotFoundException(dreamId));
    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(dream);

    mappingJacksonValue.setFilters(DreamFilters.DREAM_INCLUDE_USER_FILTER);

    return mappingJacksonValue;
  }

  @PostMapping("/dreams")
  public MappingJacksonValue createUserDream(@RequestBody String dream) {

    Map<String, Object> json = basicJsonParser.parseMap(dream);
    // User user = userRepository.findById(json.token).orElseThrow(() -> new UserNotFoundException(userId));

    User user = null;
    Dream newDream = new Dream(date, title, description, emotion, user);

    dreamRepository.save(newDream);
    user.addDream(newDream);

    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(newDream);
    mappingJacksonValue.setFilters(DreamFilters.DREAM_DEFAULT_FILTER);

    return mappingJacksonValue;
  }
}
