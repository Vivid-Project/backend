package com.vivid.backend.controller;

import java.util.Map;
import java.util.Set;

import com.vivid.backend.exceptions.AuthenticationFailedException;
import com.vivid.backend.exceptions.BadRequestException;
import com.vivid.backend.exceptions.DreamNotFoundException;
import com.vivid.backend.facades.ToneFacade;
import com.vivid.backend.filters.DreamFilters;
import com.vivid.backend.helpers.UserAuthenticationHelper;
import com.vivid.backend.model.Dream;
import com.vivid.backend.model.User;
import com.vivid.backend.repository.DreamRepository;
import com.vivid.backend.repository.ToneRepository;
import com.vivid.backend.repository.UserRepository;

import org.springframework.boot.json.BasicJsonParser;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
class UserDreamsController {

  private final UserRepository userRepository;
  private final DreamRepository dreamRepository;
  private final ToneRepository toneRepository;
  private final BasicJsonParser basicJsonParser = new BasicJsonParser();
  private final UserAuthenticationHelper userAuthenticationHelper;
  private static final String AUTH_HEADER = "authorization";

  UserDreamsController(UserRepository userRepository, DreamRepository dreamRepository, ToneRepository toneRepository) {
    this.userRepository = userRepository;
    this.dreamRepository = dreamRepository;
    this.toneRepository = toneRepository;
    this.userAuthenticationHelper = new UserAuthenticationHelper(this.userRepository);
  }

  @GetMapping(value = "/dreams")
  public MappingJacksonValue getUsersDreamsFiltered(@RequestHeader Map<String, Object> headers) {

    if (!headers.containsKey(AUTH_HEADER)) {
      throw new BadRequestException("Authorization header structure incorrect");
    }

    User user = userAuthenticationHelper.authorize(headers.get(AUTH_HEADER).toString());

    Set<Dream> dreams = dreamRepository.findAllByUser(user);
    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(dreams);

    mappingJacksonValue.setFilters(DreamFilters.DREAM_DEFAULT_FILTER);

    return mappingJacksonValue;
  }

  @GetMapping(value = "/dreams", params = { "dateStart", "dateEnd" })
  public MappingJacksonValue getUsersDreamsByDateRange(@RequestHeader Map<String, Object> headers,
      @RequestParam(name = "dateStart") String dateStart, @RequestParam(name = "dateEnd") String dateEnd) {

    User user = userAuthenticationHelper.authorize(headers.get(AUTH_HEADER).toString());

    Set<Dream> dreams = dreamRepository.findByUserAndDateBetween(user, dateStart, dateEnd);
    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(dreams);

    mappingJacksonValue.setFilters(DreamFilters.DREAM_DEFAULT_FILTER);

    return mappingJacksonValue;
  }

  @GetMapping("dreams/{dreamId}")
  public MappingJacksonValue getUserDreamFiltered(@RequestHeader Map<String, Object> headers,
      @PathVariable Long dreamId) {

    User user = userAuthenticationHelper.authorize(headers.get(AUTH_HEADER).toString());

    Dream dream = dreamRepository.findByUserAndId(user, dreamId).orElseThrow(() -> new DreamNotFoundException(dreamId));
    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(dream);

    mappingJacksonValue.setFilters(DreamFilters.DREAM_INCLUDE_USER_FILTER);

    return mappingJacksonValue;
  }

  @PostMapping("/dreams")
  public MappingJacksonValue createUserDream(@RequestBody String dream, @RequestHeader Map<String, Object> headers) {

    Map<String, Object> json = basicJsonParser.parseMap(dream);

    User user;
    try {
      user = userAuthenticationHelper.authorize(headers.get(AUTH_HEADER).toString());
    } catch (NullPointerException e) {
      throw new AuthenticationFailedException("Authorization header not present");
    }

    Dream newDream = new Dream(json.get("date").toString(), json.get("title").toString(),
        json.get("description").toString(), json.get("emotion").toString(), user);

    dreamRepository.save(newDream);
    user.addDream(newDream);

    ToneFacade.getTones(newDream, toneRepository);

    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(newDream);
    mappingJacksonValue.setFilters(DreamFilters.DREAM_DEFAULT_FILTER);

    return mappingJacksonValue;
  }
}
