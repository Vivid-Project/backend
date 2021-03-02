package com.vivid.backend;

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

  // // Is before each block even necessary?
  // @BeforeEach
  // public void setUp() throws Exception {
  @Autowired
  UserRepository userRepository;
  // User user4 = userRepository.save(new User("Sarah Wiess", "sarah@example.com", "password", "1234"));
  // Optional<User> optionalUser = userRepository.findByEmail("mjones@example.com");
  // User user0 = optionalUser.get();
  // User user1 = new User("Mike Jones", "mjones@example.com", "password", "VzYqxYC3tM0KzfSD1moEXMJHGtnegkhP");
  User user2 = new User("Ava Drew", "adrew@example.com", "password");

  // Dream dream1 = new Dream("02/22/2021", "Cool Dream", "This is a good dream", "Happy", user1);
  // Dream dream2 = new Dream("02/23/2021", "Bad Dream", "This was scary", "Bad", user1);
  Dream dream3 = new Dream("02/22/2021", "Weird Dream", "This was very weird", "Fun", user2);

  // Theme theme1 = new Theme(user1, "Bus");
  // Theme theme2 = new Theme(user2, "Train");

  // dream1.addTheme(theme1);
  // dream2.addTheme(theme1);
  // dream3.addTheme(theme2);
  // }

  // @SpringBootTest
  // @AutoConfigureMockMvc
  // public class TestingUserDreamsControllerTests {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("Getting a user's dreams fails without a token")
  public void testUserDreamsFailWithoutToken() throws Exception {

    this.mockMvc.perform(get("/dreams")).andExpect(status().isBadRequest());

    // .andExpect(content().array(containsString(substring);

  }

  @Test
  @DisplayName("Getting a user's dreams succeeds")
  public void testUserDreamsReturn() throws Exception {

    Optional<User> optionalUser = userRepository.findByEmail("mjones@example.com");
    User testUser = optionalUser.get();

    Dream dream1 = new Dream("02/22/2021", "Test dream", "Testing", "Happy", testUser);

    testUser.addDream(dream1);
    // Dream dream2 = new Dream("02/23/2021", "Bad Dream", "This was scary", "Bad", testUser);

    HttpHeaders headers = new HttpHeaders();
    headers.add("authorization", "Bearer " + testUser.getToken());

    this.mockMvc.perform(get("/dreams").headers(headers)).andExpect(status().isOk())
    
    // ADD MORE ASSERTIONS FOR DREAM COUNTS
    ;

  }
  // }
}
