package com.vivid.backend;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import com.vivid.backend.model.Dream;
import com.vivid.backend.model.User;
import com.vivid.backend.repository.UserRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class UserDreamsControllerTest {

  @Autowired
  UserRepository userRepository;

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("Getting a user's dreams fails without a token")
  public void testUserDreamsFailWithoutToken() throws Exception {

    this.mockMvc.perform(get("/dreams")).andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("Getting a user's dreams succeeds")
  public void testUserDreamsReturn() throws Exception {

    Optional<User> optionalUser = userRepository.findByEmail("mjones@example.com");
    User testUser = optionalUser.get();

    Dream dream1 = new Dream("02/22/2021", "Test dream", "Testing", "Happy", testUser);

    testUser.addDream(dream1);

    HttpHeaders headers = new HttpHeaders();
    headers.add("authorization", "Bearer " + testUser.getToken());
    
    String json = "[{'id': 3,'date': '2021/02/23','title': 'Cool Dream','description': 'This is a good dream','emotion': 'Happy','themes': [],'toneAnalysis': {'tone_strength': {},'unique_tones': ''}},{'id': 4,'date': '2021/02/24','title': 'Bad Dream','description': 'This was scary','emotion': 'Bad','themes': [],'toneAnalysis': {'tone_strength': {},'unique_tones': ''}},{'id': 5,'date': '2021/02/25','title': 'Ok Dream','description': 'This dream was meh','emotion': 'Meh','themes': [],'toneAnalysis': {'tone_strength': {},'unique_tones': ''}}]";

    this.mockMvc.perform(get("/dreams").headers(headers))
                .andExpect(status().isOk())
                .andExpect(content().json(json));
  }
  
  @Test
  @DisplayName("Getting a user's single dream succeeds")
  public void testItCanReturnAsingleUserDream() throws Exception {

    Optional<User> optionalUser = userRepository.findByEmail("mjones@example.com");
    User testUser = optionalUser.get();

    HttpHeaders headers = new HttpHeaders();
    headers.add("authorization", "Bearer " + testUser.getToken());
    
    String json = "{'id': 3,'user': {'id': 1,'name': 'Mike Jones','email': 'mjones@example.com','passwordDigest': null},'date': '2021/02/23','title': 'Cool Dream','description': 'This is a good dream','emotion': 'Happy','themes': [],'toneAnalysis': {'tone_strength': {},'unique_tones': ''}}";

    this.mockMvc.perform(get("/dreams/3").headers(headers))
                .andExpect(status().isOk())
                .andExpect(content().json(json));
  }
}
