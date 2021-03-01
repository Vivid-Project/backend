package com.vivid.backend;

import com.vivid.backend.model.Dream;
import com.vivid.backend.model.Theme;
import com.vivid.backend.model.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserDreamsControllerTest {

  // Is before each block even necessary?
  @BeforeEach
  public void setUp() throws Exception {
    User user1 = new User("Mike Jones", "mjones@example.com", "password");
    User user2 = new User("Ava Drew", "adrew@example.com", "password");

    Dream dream1 = new Dream("02/22/2021", "Cool Dream", "This is a good dream", "Happy", user1);
    Dream dream2 = new Dream("02/23/2021", "Bad Dream", "This was scary", "Bad", user1);
    Dream dream3 = new Dream("02/22/2021", "Weird Dream", "This was very weird", "Fun", user2);

    Theme theme1 = new Theme(user1, "Bus");
    Theme theme2 = new Theme(user2, "Train");

    dream1.addTheme(theme1);
    dream2.addTheme(theme1);
    dream3.addTheme(theme2);
  }

  // @SpringBootTest
  // @AutoConfigureMockMvc
  // public class TestingUserDreamsControllerTests {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("It can return a user's dreams")
  public void testReturnUserDreams() throws Exception {

    this.mockMvc.perform(get("/dreams")).andExpect(status().isBadRequest());

    // .andExpect(content().array(containsString(substring);

  }
  // }
}
